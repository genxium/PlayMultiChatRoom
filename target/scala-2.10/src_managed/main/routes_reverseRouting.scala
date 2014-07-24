// @SOURCE:/Users/user/play-2.2.2/MultiChatRooms/conf/routes
// @HASH:7a74b2d35c71c65d86fcd6545549b93dd231770c
// @DATE:Sun Jun 22 20:25:56 HKT 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:12
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers {

// @LINE:12
class ReverseAssets {
    

// @LINE:12
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def chatRoomJs(roomId:Integer = null, username:String = null): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/javascripts/chatroom.js" + queryString(List(if(roomId == null) None else Some(implicitly[QueryStringBindable[Integer]].unbind("roomId", roomId)), if(username == null) None else Some(implicitly[QueryStringBindable[String]].unbind("username", username)))))
}
                                                

// @LINE:8
def join(roomId:Integer = null, username:String = null): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "room/join" + queryString(List(if(roomId == null) None else Some(implicitly[QueryStringBindable[Integer]].unbind("roomId", roomId)), if(username == null) None else Some(implicitly[QueryStringBindable[String]].unbind("username", username)))))
}
                                                

// @LINE:7
def chatRoom(roomId:Integer = null, username:String = null): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "room" + queryString(List(if(roomId == null) None else Some(implicitly[QueryStringBindable[Integer]].unbind("roomId", roomId)), if(username == null) None else Some(implicitly[QueryStringBindable[String]].unbind("username", username)))))
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                
    
}
                          
}
                  


// @LINE:12
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:12
class ReverseAssets {
    

// @LINE:12
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def chatRoomJs : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.chatRoomJs",
   """
      function(roomId,username) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/javascripts/chatroom.js" + _qS([(roomId == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("roomId", roomId)), (username == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("username", username))])})
      }
   """
)
                        

// @LINE:8
def join : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.join",
   """
      function(roomId,username) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "room/join" + _qS([(roomId == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("roomId", roomId)), (username == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("username", username))])})
      }
   """
)
                        

// @LINE:7
def chatRoom : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.chatRoom",
   """
      function(roomId,username) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "room" + _qS([(roomId == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("roomId", roomId)), (username == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("username", username))])})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:12
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.ref {


// @LINE:12
class ReverseAssets {
    

// @LINE:12
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def chatRoomJs(roomId:Integer, username:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.chatRoomJs(roomId, username), HandlerDef(this, "controllers.Application", "chatRoomJs", Seq(classOf[Integer], classOf[String]), "GET", """""", _prefix + """assets/javascripts/chatroom.js""")
)
                      

// @LINE:8
def join(roomId:Integer, username:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.join(roomId, username), HandlerDef(this, "controllers.Application", "join", Seq(classOf[Integer], classOf[String]), "GET", """""", _prefix + """room/join""")
)
                      

// @LINE:7
def chatRoom(roomId:Integer, username:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.chatRoom(roomId, username), HandlerDef(this, "controllers.Application", "chatRoom", Seq(classOf[Integer], classOf[String]), "GET", """""", _prefix + """room""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      
    
}
                          
}
        
    