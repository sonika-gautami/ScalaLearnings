abstract class IntSet {
  def inc(x: Int): IntSet

  def contains(x: Int): Boolean

  def union(other: IntSet): IntSet
}

//binary tree: empty set & non-empty set
class NonEmpty(ele: Int, left: IntSet, right: IntSet) extends IntSet {
  def inc(x: Int): IntSet =
    if (x < ele) new NonEmpty(ele, left inc x, right)
    else if (x > ele) new NonEmpty(ele, left, right inc x)
    else this

  def contains(x: Int): Boolean =
    if (x < ele) left contains x
    else if (x > ele) right contains x
    else true


  override def toString: String = s"L:${left.toString} - $ele - R:${right.toString}"

  override def union(other: IntSet) = ((left union right) union other) inc ele
}


//singleton object
object Empty extends IntSet {
  def contains(x: Int): Boolean = false

  def inc(x: Int): IntSet = new NonEmpty(x, Empty, Empty)

  override def toString: String = "Nil"

  override def union(other: IntSet) = other
}


val v1 = new NonEmpty(3, Empty, Empty)

val v2 = v1 inc 5 inc 1 inc 7 inc 4
val v3 = v1 inc 10 inc -10 inc 20 inc -20

v2 union v3


//base class of NonEmpty = IntSet, Object


//Open Questions:
// Objects can be implemented as higher order functions
// Higher order functions can be implemented as Objects


//Nothing is the subtype of every other type in Scala
//abnormal terminations/ throwing of exceptions ->
//  all this are considered as returning Nothing.
//throw Exception : This expression is evaluated as Nothing

def error = throw new Error("any error")

//Null subtype of all AnyRef & not for AnyVal
val a = null
val b : String = a
//val c : Int = a //compiler error


trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Nil[T] extends List[T] {
  override def isEmpty = true

  override def head = throw new NoSuchElementException("Nil.head")

  override def tail = throw new NoSuchElementException("Nil.tail")
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  override def isEmpty = false
}

def singletonList[T](ele: T) = new Cons[T](ele, new Nil[T])

val l1  = singletonList[Int](1)
singletonList[String]("1")
singletonList(true)

//type erasure

def nth(n: Int, l: List[Int]) : Int = {
  def loop(a: Int, list: List[Int]): Int = {
    if(list.isEmpty) throw new IndexOutOfBoundsException("exception")
    else if( a == n-1) list.head
    else loop(a - 1, list.tail)
  }

  loop(0, l)
}

def nth2(n: Int, list: List[Int]) : Int = {
    if(list.isEmpty) throw new IndexOutOfBoundsException("exception")
    else if(n == 0) list.head
    else nth2(n - 1, list.tail)
}

nth(1, l1)
//nth(3, l1)

nth2(0, l1)
//nth2(3, l1)