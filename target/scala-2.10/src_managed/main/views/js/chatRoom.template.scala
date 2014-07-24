
package views.js

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.js._
/**/
object chatRoom extends BaseScalaTemplate[play.api.templates.JavaScriptFormat.Appendable,Format[play.api.templates.JavaScriptFormat.Appendable]](play.api.templates.JavaScriptFormat) with play.api.templates.Template2[Integer,String,play.api.templates.JavaScriptFormat.Appendable] {

    /**/
    def apply/*1.2*/(roomId: Integer, username: String):play.api.templates.JavaScriptFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.37*/("""

var g_selectRoomId=null;

$(function() """),format.raw/*5.14*/("""{"""),format.raw/*5.15*/("""

    g_selectRoomId=$("#roomId");
    g_selectRoomId.val("""),_display_(Seq[Any](/*8.25*/roomId)),format.raw/*8.31*/(""");
    g_selectRoomId.on("change", function(evt) """),format.raw/*9.47*/("""{"""),format.raw/*9.48*/("""
        evt.preventDefault();
        var value=$(this).val();
        var param="""),format.raw/*12.19*/("""{"""),format.raw/*12.20*/("""}"""),format.raw/*12.21*/(""";
        param['username']=""""),_display_(Seq[Any](/*13.29*/username)),format.raw/*13.37*/("""";
        param['roomId']=value;
        if(value!="""),_display_(Seq[Any](/*15.20*/roomId)),format.raw/*15.26*/(""")"""),format.raw/*15.27*/("""{"""),format.raw/*15.28*/("""
            $.ajax("""),format.raw/*16.20*/("""{"""),format.raw/*16.21*/("""
                type: "GET",
                url: "/room", 
                data: param,
                success: function(data, status, xhr)"""),format.raw/*20.53*/("""{"""),format.raw/*20.54*/("""
                    $("#content").html(data);
                """),format.raw/*22.17*/("""}"""),format.raw/*22.18*/(""",
                error: function(xhr, status, err)"""),format.raw/*23.50*/("""{"""),format.raw/*23.51*/("""

                """),format.raw/*25.17*/("""}"""),format.raw/*25.18*/("""
            """),format.raw/*26.13*/("""}"""),format.raw/*26.14*/(""")
        """),format.raw/*27.9*/("""}"""),format.raw/*27.10*/("""
    """),format.raw/*28.5*/("""}"""),format.raw/*28.6*/(""");

    var WS = window['MozWebSocket'] ? MozWebSocket : WebSocket
    var chatSocket = new WS(""""),_display_(Seq[Any](/*31.31*/routes/*31.37*/.Application.join(roomId, username).webSocketURL(request))),format.raw/*31.94*/("""")

    var sendMessage = function() """),format.raw/*33.34*/("""{"""),format.raw/*33.35*/("""
        chatSocket.send(JSON.stringify(
            """),format.raw/*35.13*/("""{"""),format.raw/*35.14*/("""text: $("#talk").val()"""),format.raw/*35.36*/("""}"""),format.raw/*35.37*/("""
        ))
        $("#talk").val('')
    """),format.raw/*38.5*/("""}"""),format.raw/*38.6*/("""

    var receiveEvent = function(event) """),format.raw/*40.40*/("""{"""),format.raw/*40.41*/("""
        var data = JSON.parse(event.data)

        // Handle errors
        if(data.error) """),format.raw/*44.24*/("""{"""),format.raw/*44.25*/("""
            chatSocket.close()
            $("#onError span").text(data.error)
            $("#onError").show()
            return
        """),format.raw/*49.9*/("""}"""),format.raw/*49.10*/(""" else """),format.raw/*49.16*/("""{"""),format.raw/*49.17*/("""
            $("#onChat").show()
        """),format.raw/*51.9*/("""}"""),format.raw/*51.10*/("""

        // Create the message element
        var el = $('<div class="message"><span></span><p></p></div>')
        $("span", el).text(data.user)
        $("p", el).text(data.message)
        $(el).addClass(data.kind)
        if(data.user == '"""),_display_(Seq[Any](/*58.27*/username)),format.raw/*58.35*/("""') $(el).addClass('me')
        $('#messages').append(el)

        // Update the members list
        $("#members").html('')
        $(data.members).each(function() """),format.raw/*63.41*/("""{"""),format.raw/*63.42*/("""
            var li = document.createElement('li');
            li.textContent = this;
            $("#members").append(li);
        """),format.raw/*67.9*/("""}"""),format.raw/*67.10*/(""")
    """),format.raw/*68.5*/("""}"""),format.raw/*68.6*/("""

    var handleReturnKey = function(e) """),format.raw/*70.39*/("""{"""),format.raw/*70.40*/("""
        if(e.charCode == 13 || e.keyCode == 13) """),format.raw/*71.49*/("""{"""),format.raw/*71.50*/("""
            e.preventDefault()
            sendMessage()
        """),format.raw/*74.9*/("""}"""),format.raw/*74.10*/("""
    """),format.raw/*75.5*/("""}"""),format.raw/*75.6*/("""

    $("#talk").keypress(handleReturnKey)

    chatSocket.onmessage = receiveEvent

"""),format.raw/*81.1*/("""}"""),format.raw/*81.2*/(""")
"""))}
    }
    
    def render(roomId:Integer,username:String): play.api.templates.JavaScriptFormat.Appendable = apply(roomId,username)
    
    def f:((Integer,String) => play.api.templates.JavaScriptFormat.Appendable) = (roomId,username) => apply(roomId,username)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Jun 22 20:25:58 HKT 2014
                    SOURCE: /Users/user/play-2.2.2/MultiChatRooms/app/views/chatRoom.scala.js
                    HASH: d9178571bdff48793925fa20a7f7542a682d20ea
                    MATRIX: 805->1|940->36|1008->77|1036->78|1130->137|1157->143|1233->192|1261->193|1371->275|1400->276|1429->277|1495->307|1525->315|1614->368|1642->374|1671->375|1700->376|1748->396|1777->397|1947->539|1976->540|2067->603|2096->604|2175->655|2204->656|2250->674|2279->675|2320->688|2349->689|2386->699|2415->700|2447->705|2475->706|2608->803|2623->809|2702->866|2767->903|2796->904|2877->957|2906->958|2956->980|2985->981|3055->1024|3083->1025|3152->1066|3181->1067|3301->1159|3330->1160|3497->1300|3526->1301|3560->1307|3589->1308|3657->1349|3686->1350|3968->1596|3998->1604|4191->1769|4220->1770|4380->1903|4409->1904|4442->1910|4470->1911|4538->1951|4567->1952|4644->2001|4673->2002|4766->2068|4795->2069|4827->2074|4855->2075|4967->2160|4995->2161
                    LINES: 26->1|29->1|33->5|33->5|36->8|36->8|37->9|37->9|40->12|40->12|40->12|41->13|41->13|43->15|43->15|43->15|43->15|44->16|44->16|48->20|48->20|50->22|50->22|51->23|51->23|53->25|53->25|54->26|54->26|55->27|55->27|56->28|56->28|59->31|59->31|59->31|61->33|61->33|63->35|63->35|63->35|63->35|66->38|66->38|68->40|68->40|72->44|72->44|77->49|77->49|77->49|77->49|79->51|79->51|86->58|86->58|91->63|91->63|95->67|95->67|96->68|96->68|98->70|98->70|99->71|99->71|102->74|102->74|103->75|103->75|109->81|109->81
                    -- GENERATED --
                */
            