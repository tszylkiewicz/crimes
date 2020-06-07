// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Repository/crimes/conf/routes
// @DATE:Sun Jun 07 17:13:17 CEST 2020

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  HomeController_2: controllers.HomeController,
  // @LINE:9
  CrimeController_0: controllers.CrimeController,
  // @LINE:13
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    HomeController_2: controllers.HomeController,
    // @LINE:9
    CrimeController_0: controllers.CrimeController,
    // @LINE:13
    Assets_1: controllers.Assets
  ) = this(errorHandler, HomeController_2, CrimeController_0, Assets_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_2, CrimeController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """crimes""", """controllers.CrimeController.listCrimes"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """crimes""", """controllers.CrimeController.createCrime"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_2.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_CrimeController_listCrimes1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("crimes")))
  )
  private[this] lazy val controllers_CrimeController_listCrimes1_invoker = createInvoker(
    CrimeController_0.listCrimes,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CrimeController",
      "listCrimes",
      Nil,
      "GET",
      this.prefix + """crimes""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_CrimeController_createCrime2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("crimes")))
  )
  private[this] lazy val controllers_CrimeController_createCrime2_invoker = createInvoker(
    CrimeController_0.createCrime,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CrimeController",
      "createCrime",
      Nil,
      "POST",
      this.prefix + """crimes""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_Assets_versioned3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned3_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_2.index)
      }
  
    // @LINE:9
    case controllers_CrimeController_listCrimes1_route(params@_) =>
      call { 
        controllers_CrimeController_listCrimes1_invoker.call(CrimeController_0.listCrimes)
      }
  
    // @LINE:10
    case controllers_CrimeController_createCrime2_route(params@_) =>
      call { 
        controllers_CrimeController_createCrime2_invoker.call(CrimeController_0.createCrime)
      }
  
    // @LINE:13
    case controllers_Assets_versioned3_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned3_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
