

def concat[T](xs: List[T], ys: List[T]): List[T] =
  (xs.foldRight(ys)((t, ts) => {
    println(t.getClass + " " + ts.getClass)
    t :: ts
  }))

concat[Int](List(1,2,3), List(11,22,33))


def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldRight List[U]()) ((t : T, u: List[U]) => f(t) :: u)

mapFun(List(1, 2, 3, 4), (x : Int) => s"Int:$x")


def lengthFun[T](xs: List[T]): Int =
  (xs foldRight 0) ((t: T, acc : Int) => acc + 1)

lengthFun(List(1, 2, 3, 4))



def foldLeft[U, T](a: U)(op: (U, T) => U)(list: List[T]): U = list match {
  case Nil => a
  case x :: xs => foldLeft(op(a, x))(op)(xs)
}

def reduceLeft[T](op: (T, T) => T)(list: List[T]): T = list match {
  case Nil => throw new Error("reduce on empty")
  case x :: xs => foldLeft(x)(op)(xs)
}



foldLeft[Int, Int](0)((x, y) => x + y)(List(1, 2, 3, 4))
foldLeft[String, Int]("-->")((x, y) => x + y)(List(1, 2, 3, 4))

reduceLeft[Int]((x, y) => x + y)(List(1, 2, 3, 4))
reduceLeft[String]((x, y) => x + y)(List(1, 2, 3, 4).map(_.toString))


def reduceRight[T](op: (T, T) => T)(list: List[T]): T = list match {
  case Nil => throw new Error("reduce on empty")
  case x :: xs => op(x, reduceRight(op)(xs))
}

reduceRight[Int]((x, y) => x + y)(List(1, 2, 3, 4))
reduceRight[String]((x, y) => x + y)(List(1, 2, 3, 4).map(_.toString))


def foldRight[T, U](a: U)(op: (U, T) => U)(list: List[T]): U = list match {
  case Nil => a
  case x :: Nil => op(a, x)
  case x :: xs => op(x, foldRight(a)(op)(xs))
}

foldRight[Int, Int](0)((x, y) => x + y)(List(1, 2, 3, 4))
foldRight[Int, String]("-->")((x, y) => x + y)(List(1, 2, 3, 4))
List(1, 2, 3, 4).foldRight("-->")((x, y) => x + y)
List(1, 2, 3, 4).foldLeft("-->")((x, y) => x + y)







