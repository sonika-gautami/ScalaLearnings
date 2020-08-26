import scala.annotation.tailrec

def abs(d: Double) = if (d < 0) -d else d

def guessOk(g: Double, x: Double): Boolean = abs(g * g - x) / x < 0.001

def improve(g: Double, x: Double): Double = (g + (x / g)) / 2

def sqrt_(g: Double, x: Double): Double =
  if (guessOk(g, x)) g else sqrt_(improve(g, x), x)


def sqrt(x: Double): Double = sqrt_(1.0, x)

sqrt(2)

sqrt(0.001)

sqrt(0.1e-20)

sqrt(1.0e20)

sqrt(1.0e50)


def gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y)

gcd(14, 21)

def fact(x: Int) : Int = {
  @tailrec
  def factIn(acc: Int, y: Int) : Int = if(y == 0) acc else factIn(acc*y, y-1)

  factIn(1, x)
}

fact(5)