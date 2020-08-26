package scala_cats



trait Json

final case class JsonString(j: String) extends Json

final case class JsonInt(j: Int) extends Json

final case class JsonMap(j: Map[String, Json]) extends Json

case object JsonNull extends Json

//'type class'
//As this trait has at-least one type parameter -> this is 'type class'
trait JsWriter[T] {
  def write(value: T): Json
}

case class Person(name: String, age: Int)


//'type class instances' = instances of 'type class'
//in scala implemented with:
//  concrete implementations & implicit keyword


object JsWriterImpProvider {

  implicit val JsIntWriter: JsWriter[Int] = (value: Int) => JsonInt(value)

  implicit val JsStringWriter: JsWriter[String] = (value: String) => JsonString(value)

  implicit val JsObjectWriter: JsWriter[Person] = (value: Person) => JsonMap(
    Map("name" -> JsonString(value.name), "age" -> JsonInt(value.age)))
}


//'type class interface'
//= functionality to expose to users
//It has methods with:
//    Generic types & 'type class instances' as implicit params
//There are two ways:
//  1] interface objects
//  2] interface syntax

//1] interface objects = methods declared in Singleton object
object anyJson {
  def toJson[T](value: T)(implicit jsWriter: JsWriter[T]) = jsWriter.write(value)
}

//2] inteface syntax = extend the class with extension methods
object anyJson2 {

  implicit class jsonWriterProvider[T](value: T) {
    def toJson(implicit jsWriter: JsWriter[T]) = jsWriter.write(value)
  }

}