package coursera

object week6 extends App {

  def unSafe(col: Int, q: List[Int]): Boolean = {

    def unsafeCol(c: Int) = col == c

    def unsafeDia(c: Int, r: Int) = Math.abs(col - c) == Math.abs(row - r)

    def row = q.length

    q.zip(row - 1 to 0 by -1).exists{ case (c, r) =>
      unsafeCol(c) || unsafeDia(c, r)
    }
  }

  def queens(n: Int): Set[List[Int]] = {
    def place(k: Int): Set[List[Int]] = {
      if (k == 0) Set(Nil)
      else {
        for {
          q <- place(k - 1)
          col <- 0 until n
          if !unSafe(col, q)
        } yield col :: q
      }
    }

    place(n)
  }

  println(queens(4))

}
