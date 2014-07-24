package controllers;

import akka.actor.ActorRef;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.ChatRoom;
import play.libs.Akka;
import play.libs.F;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import views.html.chatRoom;
import views.html.index;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static akka.pattern.Patterns.ask;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Application extends Controller {
  
    static ConcurrentHashMap<Integer, ActorRef> s_rooms=new ConcurrentHashMap<Integer, ActorRef>(){{
        put(0, Akka.system().actorOf(ChatRoom.props(0)));
        put(1, Akka.system().actorOf(ChatRoom.props(1)));
        put(2, Akka.system().actorOf(ChatRoom.props(2)));
    }};

    /**
     * Display the home page.
     */
    public static Result index() {
        return ok(index.render());
    }
  
    /**
     * Display the chat room.
     */
    public static Result chatRoom(Integer roomId, String username) {
        if(username == null || username.trim().equals("")) {
            flash("error", "Please choose a valid username.");
            return redirect(routes.Application.index());
        }
        return ok(chatRoom.render(roomId, username)); // default roomId is 0
    }

    public static Result chatRoomJs(Integer roomId, String username) {
        return ok(views.js.chatRoom.render(roomId, username));
    }
    
    /**
     * Handle the chat websocket.
     */
    public static WebSocket<JsonNode> join(final Integer roomId, final String username) {
        return new WebSocket<JsonNode>() {
            
            // Called when the Websocket Handshake is done.
            public void onReady(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out){
                
                // Join the chat room.
                try { 
                    join(roomId, username, in, out);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }
  
    public static void join(final int roomId, final String username, WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) throws Exception{
        for(Map.Entry<Integer,ActorRef> entry : s_rooms.entrySet()){
            ActorRef rm=entry.getValue();
            rm.tell(new ChatRoom.Quit(username), null);
        }
        final ActorRef room = s_rooms.get(roomId);
        String result = (String) Await.result(ask(room, new ChatRoom.Join(username, out), 1000), Duration.create(1, SECONDS));

        if("OK".equals(result)) {

            // For each event received on the socket,
            in.onMessage(new F.Callback<JsonNode>() {
                public void invoke(JsonNode event) {
                    // Send a Talk message to the room.
                    room.tell(new ChatRoom.Talk(username, event.get("text").asText()), null);
                }
            });

            // When the socket is closed.
            in.onClose(new F.Callback0() {
                public void invoke() {
                    // Send a Quit message to the room.
                    room.tell(new ChatRoom.Quit(username), null);
                }
            });

        } else {

            // Cannot connect, create a Json error.
            ObjectNode error = Json.newObject();
            error.put("error", result);

            // Send the error to the socket.
            out.write(error);

        }
    }
}
