// @GENERATOR:play-routes-compiler
// @SOURCE:D:/Repository/crimes/conf/routes
// @DATE:Sun Jun 07 17:13:17 CEST 2020

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseCrimeController CrimeController = new controllers.ReverseCrimeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseCrimeController CrimeController = new controllers.javascript.ReverseCrimeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}
