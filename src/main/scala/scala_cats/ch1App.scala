package scala_cats

object ch1App extends App {

  println(method1)
  println(method2)
  println(useOfimplicitly)

  //usage of type interface method 1:
  lazy val method1 = {
    import JsWriterImpProvider.JsIntWriter //implicit implementation to scope of below usage
    anyJson.toJson(1)
  }

  //usage of type interface method 2:
  lazy val method2 = {
    import JsWriterImpProvider._ //implicit implementation to scope of below usage
    import anyJson2.jsonWriterProvider  //extend class with toJson method

    Person("type interface", 1).toJson
  }

  //usage of implicitly
  lazy val useOfimplicitly = {
    import JsWriterImpProvider._

    //we can have make call to implicit implementation with implicitly
    val value: JsWriter[Int] = implicitly[JsWriter[Int]]
    //or else we must have to make a call as below:
    // we must be knowing who has implementation for JsWriter[Int]
    val value2 : JsWriter[Int] = JsIntWriter

    value.write(1) -> value2.write(1)
  }
}
