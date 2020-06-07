
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object listCrimes extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Seq[Crime],Form[CrimeForm.Data],Call,MessagesRequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(crimes: Seq[Crime], form: Form[CrimeForm.Data], postUrl: Call)(implicit request: MessagesRequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.106*/("""

  """),_display_(/*3.4*/main("Crimes")/*3.18*/ {_display_(Seq[Any](format.raw/*3.20*/("""

    """),format.raw/*5.5*/("""<h1>Crimes</h1>

    """),format.raw/*7.40*/("""
    """),_display_(/*8.6*/request/*8.13*/.flash.data.map/*8.28*/{ case (name, value) =>_display_(Seq[Any](format.raw/*8.51*/("""
      """),format.raw/*9.7*/("""<div>"""),_display_(/*9.13*/name),format.raw/*9.17*/(""": """),_display_(/*9.20*/value),format.raw/*9.25*/("""</div>
    """)))}),format.raw/*10.6*/("""

    """),format.raw/*12.5*/("""<table>
      <thead>
        <tr><th>Category</th><th>Description</th><th>City</th><th>Date</th>
      </thead>
      <tbody>
      """),_display_(/*17.8*/for(w <- crimes) yield /*17.24*/ {_display_(Seq[Any](format.raw/*17.26*/("""
        """),format.raw/*18.9*/("""<tr><td>"""),_display_(/*18.18*/w/*18.19*/.category),format.raw/*18.28*/("""</td><td>"""),_display_(/*18.38*/w/*18.39*/.description),format.raw/*18.51*/("""</td><td>"""),_display_(/*18.61*/w/*18.62*/.city),format.raw/*18.67*/("""</td><td>"""),_display_(/*18.77*/w/*18.78*/.date),format.raw/*18.83*/("""</td></tr>
      """)))}),format.raw/*19.8*/("""
      """),format.raw/*20.7*/("""</tbody>
    </table>

    <hr/>

    """),format.raw/*25.66*/("""
    """),_display_(/*26.6*/if(form.hasGlobalErrors)/*26.30*/ {_display_(Seq[Any](format.raw/*26.32*/("""
      """),_display_(/*27.8*/form/*27.12*/.globalErrors.map/*27.29*/ { error: FormError =>_display_(Seq[Any](format.raw/*27.51*/("""
        """),format.raw/*28.9*/("""<div>
          """),_display_(/*29.12*/error/*29.17*/.key),format.raw/*29.21*/(""": """),_display_(/*29.24*/error/*29.29*/.message),format.raw/*29.37*/("""
        """),format.raw/*30.9*/("""</div>
      """)))}),format.raw/*31.8*/("""
    """)))}),format.raw/*32.6*/("""

    """),_display_(/*34.6*/helper/*34.12*/.form(postUrl)/*34.26*/ {_display_(Seq[Any](format.raw/*34.28*/("""
      """),_display_(/*35.8*/helper/*35.14*/.CSRF.formField),format.raw/*35.29*/("""

      """),_display_(/*37.8*/helper/*37.14*/.inputText(form("category"))),format.raw/*37.42*/("""
      """),_display_(/*38.8*/helper/*38.14*/.inputText(form("description"))),format.raw/*38.45*/("""
      """),_display_(/*39.8*/helper/*39.14*/.inputText(form("city"))),format.raw/*39.38*/("""
      """),_display_(/*40.8*/helper/*40.14*/.inputText(form("date"))),format.raw/*40.38*/("""

      """),format.raw/*42.7*/("""<button>Add crime</button>
    """)))}),format.raw/*43.6*/("""
  """)))}))
      }
    }
  }

  def render(crimes:Seq[Crime],form:Form[CrimeForm.Data],postUrl:Call,request:MessagesRequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(crimes,form,postUrl)(request)

  def f:((Seq[Crime],Form[CrimeForm.Data],Call) => (MessagesRequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (crimes,form,postUrl) => (request) => apply(crimes,form,postUrl)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-06-07T17:56:12.635503500
                  SOURCE: D:/Repository/crimes/app/views/listCrimes.scala.html
                  HASH: 94a333504f1d32e5e57f3862857b49df4a5cec47
                  MATRIX: 786->1|986->105|1018->112|1040->126|1079->128|1113->136|1163->194|1195->201|1210->208|1233->223|1293->246|1327->254|1359->260|1383->264|1412->267|1437->272|1480->285|1515->293|1680->432|1712->448|1752->450|1789->460|1825->469|1835->470|1865->479|1902->489|1912->490|1945->502|1982->512|1992->513|2018->518|2055->528|2065->529|2091->534|2140->553|2175->561|2246->665|2279->672|2312->696|2352->698|2387->707|2400->711|2426->728|2486->750|2523->760|2568->778|2582->783|2607->787|2637->790|2651->795|2680->803|2717->813|2762->828|2799->835|2834->844|2849->850|2872->864|2912->866|2947->875|2962->881|2998->896|3035->907|3050->913|3099->941|3134->950|3149->956|3201->987|3236->996|3251->1002|3296->1026|3331->1035|3346->1041|3391->1065|3428->1075|3491->1108
                  LINES: 21->1|26->1|28->3|28->3|28->3|30->5|32->7|33->8|33->8|33->8|33->8|34->9|34->9|34->9|34->9|34->9|35->10|37->12|42->17|42->17|42->17|43->18|43->18|43->18|43->18|43->18|43->18|43->18|43->18|43->18|43->18|43->18|43->18|43->18|44->19|45->20|50->25|51->26|51->26|51->26|52->27|52->27|52->27|52->27|53->28|54->29|54->29|54->29|54->29|54->29|54->29|55->30|56->31|57->32|59->34|59->34|59->34|59->34|60->35|60->35|60->35|62->37|62->37|62->37|63->38|63->38|63->38|64->39|64->39|64->39|65->40|65->40|65->40|67->42|68->43
                  -- GENERATED --
              */
          