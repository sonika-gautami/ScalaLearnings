//vector

for {
  i <- 1 until 5
  j <- 1 until i
  if ((i + j) % 2 == 0)
} yield (i, j)

// similar to
1 until 5 flatMap { i =>
  1 until i filter (j => (j + i) % 2 == 0) map (j => (i, j))
}


//Map in scala also extends Function Key => Value
val m = Map(1 -> "one", 2 -> "two")
m(1)

//m(4) java.util.NoSuchElementException: key not found: 4


val l = List("abc", "cdef", "yz", "pqrst")
l.sorted
l.sortWith((a1, a2) => a1.length > a2.length)
l.sortWith(_.length > _.length)
l.groupBy(_.head)

val s = "string str2"
s.groupBy(c => c).mapValues(_.length)

Seq(s, s).flatten
Seq(s, s).flatten.groupBy(c => c).mapValues(_.length)


object poly {

  class Polyn(terms: Map[Int, Double]) {

  }

  val p1 = new Polyn(Map(0 -> 5, 1 -> -2, 2 -> 3))
  val p2 = new Polyn(Map(0 -> 7, 1 -> 5, 3 -> 9))
}