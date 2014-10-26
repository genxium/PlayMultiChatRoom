package models;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Akka;
import play.libs.F;
import play.libs.Json;
import play.mvc.WebSocket;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.util.HashMap;
import java.util.Map;

import static akka.pattern.Patterns.ask;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ChatRoom extends UntypedActor {

    // Members of this room.
    protected Map<String, WebSocket.Out<JsonNode>> m_members = null;
    protected Integer m_roomId=null;

    public static Props props(final int roomId){
        return Props.create(new Creator<ChatRoom>(){

            @Override
            public ChatRoom create() throws Exception {
                return new ChatRoom(roomId);
            }
        });
    }

    public ChatRoom(int roomId){
        m_members=new HashMap<String, WebSocket.Out<JsonNode>>();
        m_roomId=roomId;
    }

    @Override
    public void onReceive(Object message) throws Exception {

        if(message instanceof Join) {

            // Received a Join message
            Join join = (Join)message;

            // Check if this username is free.
            if(m_members.containsKey(join.username)) {
                getSender().tell("This username is already used", getSelf());
            } else {
                m_members.put(join.username, join.channel);
                notifyAll("join", join.username, "has entered the room");
                getSender().tell("OK", getSelf());
            }

        } else if(message instanceof Talk)  {

            // Received a Talk message
            Talk talk = (Talk)message;
            notifyAll("talk", talk.username, talk.text);

        } else if(message instanceof Quit)  {

            // Received a Quit message
            Quit quit = (Quit)message;
            if(m_members.containsKey(quit.username)){
                m_members.remove(quit.username);
                notifyAll("quit", quit.username, "has left the room");
            }

        } else {
            unhandled(message);
        }

    }

    // Send a Json event to all members
    public void notifyAll(String kind, String user, String text) {
        for(WebSocket.Out<JsonNode> channel: m_members.values()) {

            ObjectNode event = Json.newObject();
            event.put("kind", kind);
            event.put("user", user);
            event.put("message", text);

            ArrayNode m = event.putArray("members");
            for(String u: m_members.keySet()) {
                m.add(u);
            }

            channel.write(event);
        }
    }

    // -- Messages

    public static class Join {

        final String username;
        final WebSocket.Out<JsonNode> channel;

        public Join(String username, WebSocket.Out<JsonNode> channel) {
            this.username = username;
            this.channel = channel;
        }

    }

    public static class Talk {

        final String username;
        final String text;

        public Talk(String username, String text) {
            this.username = username;
            this.text = text;
        }
    }

    public static class Quit {

        final String username;

        public Quit(String username) {
            this.username = username;
        }

    }
}
