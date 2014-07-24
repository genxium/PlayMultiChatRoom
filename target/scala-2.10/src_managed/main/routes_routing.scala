// @SOURCE:/Users/user/play-2.2.2/MultiChatRooms/conf/routes
// @HASH:7a74b2d35c71c65d86fcd6545549b93dd231770c
// @DATE:Sun Jun 22 20:25:56 HKT 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:7
private[this] lazy val controllers_Application_chatRoom1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("room"))))
        

// @LINE:8
private[this] lazy val controllers_Application_join2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("room/join"))))
        

// @LINE:9
private[this] lazy val controllers_Application_chatRoomJs3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/javascripts/chatroom.js"))))
        

// @LINE:12
private[this] lazy val controllers_Assets_at4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """room""","""controllers.Application.chatRoom(roomId:Integer ?= null, username:String ?= null)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """room/join""","""controllers.Application.join(roomId:Integer ?= null, username:String ?= null)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/javascripts/chatroom.js""","""controllers.Application.chatRoomJs(roomId:Integer ?= null, username:String ?= null)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:7
case controllers_Application_chatRoom1(params) => {
   call(params.fromQuery[Integer]("roomId", Some(null)), params.fromQuery[String]("username", Some(null))) { (roomId, username) =>
        invokeHandler(controllers.Application.chatRoom(roomId, username), HandlerDef(this, "controllers.Application", "chatRoom", Seq(classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """room"""))
   }
}
        

// @LINE:8
case controllers_Application_join2(params) => {
   call(params.fromQuery[Integer]("roomId", Some(null)), params.fromQuery[String]("username", Some(null))) { (roomId, username) =>
        invokeHandler(controllers.Application.join(roomId, username), HandlerDef(this, "controllers.Application", "join", Seq(classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """room/join"""))
   }
}
        

// @LINE:9
case controllers_Application_chatRoomJs3(params) => {
   call(params.fromQuery[Integer]("roomId", Some(null)), params.fromQuery[String]("username", Some(null))) { (roomId, username) =>
        invokeHandler(controllers.Application.chatRoomJs(roomId, username), HandlerDef(this, "controllers.Application", "chatRoomJs", Seq(classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """assets/javascripts/chatroom.js"""))
   }
}
        

// @LINE:12
case controllers_Assets_at4(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     