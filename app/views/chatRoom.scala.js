@(roomId: Integer, username: String)

var g_selectRoomId=null;

$(function() {

    g_selectRoomId=$("#roomId");
    g_selectRoomId.val(@roomId);
    g_selectRoomId.on("change", function(evt) {
        evt.preventDefault();
        var value=$(this).val();
        var param={};
        param['username']="@username";
        param['roomId']=value;
        if(value!=@roomId){
            $.ajax({
                type: "GET",
                url: "/room", 
                data: param,
                success: function(data, status, xhr){
                    $("#content").html(data);
                },
                error: function(xhr, status, err){

                }
            })
        }
    });

    var WS = window['MozWebSocket'] ? MozWebSocket : WebSocket
    var chatSocket = new WS("@routes.Application.join(roomId, username).webSocketURL(request)")

    var sendMessage = function() {
        chatSocket.send(JSON.stringify(
            {text: $("#talk").val()}
        ))
        $("#talk").val('')
    }

    var receiveEvent = function(event) {
        var data = JSON.parse(event.data)

        // Handle errors
        if(data.error) {
            chatSocket.close()
            $("#onError span").text(data.error)
            $("#onError").show()
            return
        } else {
            $("#onChat").show()
        }

        // Create the message element
        var el = $('<div class="message"><span></span><p></p></div>')
        $("span", el).text(data.user)
        $("p", el).text(data.message)
        $(el).addClass(data.kind)
        if(data.user == '@username') $(el).addClass('me')
        $('#messages').append(el)

        // Update the members list
        $("#members").html('')
        $(data.members).each(function() {
            var li = document.createElement('li');
            li.textContent = this;
            $("#members").append(li);
        })
    }

    var handleReturnKey = function(e) {
        if(e.charCode == 13 || e.keyCode == 13) {
            e.preventDefault()
            sendMessage()
        }
    }

    $("#talk").keypress(handleReturnKey)

    chatSocket.onmessage = receiveEvent

})
