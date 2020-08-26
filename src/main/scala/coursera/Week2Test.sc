

def sum(f: Int => Int, a: Int, b: Int): Int = {
  def loop(x: Int, acc: Int): Int =
    if (x > b) acc else loop(x + 1, f(x) + acc)

  loop(a, 0)
}

sum((a: Int) => a * a, 1, 3)

sum((a: Int) => a * a, 1, 3)


def sumCurr(f: Int => Int)(min: Int, max: Int): Int =
  if (min > max) 0 else f(min) + sumCurr(f)(min + 1, max)


def product(f: Int => Int)(min: Int, max: Int): Int =
  if (min > max) 1 else f(min) * product(f)(min + 1, max)

product((a: Int) => a)(1, 3)

def factorial(a: Int) = product(a => a)(1, a)

def plus(a: Int, b: Int) = a + b

def multiply(a: Int, b: Int) = a * b


def doG(f: Int => Int)(min: Int, max: Int)(unitVal: Int, op: (Int, Int) => Int): Int =
  if (min > max) unitVal else op(f(min), doG(f)(min + 1, max)(unitVal, op))

doG((a: Int) => a)(1, 4)(0, plus)

doG((a: Int) => a)(1, 4)(1, multiply)

def mapReduce(map: Int => Int, combine: (Int, Int) => Int, unitVal: Int)(min: Int, max: Int): Int =
  if (min > max) unitVal else combine(map(min), mapReduce(map, combine, unitVal)(min + 1, max))

mapReduce((a: Int) => a, plus, 0)(1, 4)
mapReduce((a: Int) => a, multiply, 1)(1, 4)

def abs(d: Double) = if (d < 0) -d else d

def findFixedPoint(f: Double => Double)(x: Double): Double = {
  def validGuess(x: Double, y: Double) = abs((x - y) / x) < 0.0001

  def loop(y: Double): Double = {
    val v = f(y)
    if (validGuess(v, y)) y
    else loop(f(v))
  }

  loop(x)
}

findFixedPoint((x: Double) => 1 + x / 2)(1)

def sqRoot1(x: Double) = findFixedPoint(y => x / y)(x)
//sqRoot1(2) in-finite loop

def avgDump(f: (Double => Double))(x: Double) = (x + f(x)) / 2

def sqRoot2(x: Double) = findFixedPoint(avgDump(y => x / y))(1)
sqRoot2(2)


