// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Repository/crimes/conf/routes
// @DATE:Sun Jun 07 17:13:17 CEST 2020


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
