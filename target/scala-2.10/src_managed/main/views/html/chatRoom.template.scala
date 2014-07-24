
package views.html

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
import views.html._
/**/
object chatRoom extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Integer,String,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(roomId: Integer, username: String):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.37*/("""

"""),_display_(Seq[Any](/*3.2*/main(username)/*3.16*/ {_display_(Seq[Any](format.raw/*3.18*/("""
    
    <div class="page-header">
        <h1>Welcome to 一齊hea聊天室 <small>You are chatting as """),_display_(Seq[Any](/*6.61*/username)),format.raw/*6.69*/("""</small></h1>
    </div>
    
    <div id="onError" class="alert-message error">
        <p>
            <strong>Oops!</strong> <span></span>
        </p>
    </div>

    <div id="onChat" class="row">
        <div class="span10" id="main">
            <div id="messages">
            </div>
            <textarea id="talk"></textarea>
        </div>
        <div class="span4">
    	    <h2>選擇聊天室</h2>
    	    <select id="roomId" name="roomId" class="medium">
		<option value=0>Qoo</option>
                <option value=1>手遊代理</option>
                <option value=2>頁遊代理</option>
    	    </select>
            <h2>當前花生友列表</h2>
            <ul id="members">
            </ul>
        </div>
    </div>
    
    <script type="text/javascript" charset="utf-8" src=""""),_display_(Seq[Any](/*34.58*/routes/*34.64*/.Application.chatRoomJs(roomId, username))),format.raw/*34.105*/(""""></script>
    
""")))})),format.raw/*36.2*/("""
"""))}
    }
    
    def render(roomId:Integer,username:String): play.api.templates.HtmlFormat.Appendable = apply(roomId,username)
    
    def f:((Integer,String) => play.api.templates.HtmlFormat.Appendable) = (roomId,username) => apply(roomId,username)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Jun 22 20:25:58 HKT 2014
                    SOURCE: /Users/user/play-2.2.2/MultiChatRooms/app/views/chatRoom.scala.html
                    HASH: 3520dfb1fae0187b82676be4dc65cc9c9e11ad5e
                    MATRIX: 785->1|914->36|951->39|973->53|1012->55|1143->151|1172->159|1976->927|1991->933|2055->974|2104->992
                    LINES: 26->1|29->1|31->3|31->3|31->3|34->6|34->6|62->34|62->34|62->34|64->36
                    -- GENERATED --
                */
            