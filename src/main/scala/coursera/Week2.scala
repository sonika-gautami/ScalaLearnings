package coursera

object Week2 extends App {

  //Function Currying
  //Each function -> is the Expression as Anonymous function which takes one argument : named as 'currying'
  def sum(f: Int => Int)(a: Int, b: Int): Int = ???

  // = def (sum(f: Int => Int))(a: Int, b: Int) : Int
  /*
    type : (Int => Int) => (Int, Int) => Int
    Functions follow Right association:
           (Int => Int) => ((Int, Int) => Int)
  */

  //sum((a: Int) => a)
  /*
  sum(f) returns a Function & a & b are arguments to this function.
   */


  //Fixed Point:
  // For some value of x, If f(x) = x then, x is the fixed number value for that function.
  /* Method to find Fixed Point:
      -> initial estimate : x
      -> repeat with : f(x), f(f(x)), f(f(fx)), ...

   */
  def id(x: Double): Double = x

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

  //new Rational(1, 0)

  /*
  require -> used to enforce a precondition on the caller of a function or the creator of an object of some class.
  assert -> used to check the code of the function itself.
   if a precondition fails, then you getan illegal argument exception.
   if an assertion fails and it's not the caller's fault and consequently you get an assertion error.
   */

  //Primary Constructor
  //= takes the arguments & executes body of class.
  //Secondary Constructors
  //= def this(args...)


  //Evaluation of method of class:
  /*
  new Class(c1, .., cn).new Method(m1, .., mn)

  1] substitution of formal params of Class  by cs1, .., csn
  2] substitution of formal params of Method by ms1, .., msn
  3] substitution of self reference (this) by value of object [new Class(v1, .., vn)]


  new Rational(1, 3).nume
  1] 1/a, 3/b
  2] nothing at method level
  3] (value of new Rational(1,3))/this

  new Rational(1, 3).less(new Rational(2, 5))
  1] 1/a, 3/b
  2] 2.1] [2/a, 5/b]
     2.2] value of new Rational(2, 5) with that
  3] value of new Rational(1, 3) with this
  4] new Rational(1, 3).nume * new Rational(2, 5).deno < new Rational(2, 5).nume * new Rational(1, 3).deno
  5] 1*5 < 2*3
  6] true
   */

  //infix operator:
  /*
  Any method with A parameter -> can be used with infix operator
  rational1 less rational2  is same as rational1.less(rational2)
   */

  //Identifiers:
  /*
  1] Alphanumeric [A-Za-z0-9][A-Za-z0-9_]+   e.g x1
  2] symbolic [OperatorSymbol][OtherSymbols]+     e.g *, +, +:, +?*%&
  3] Alphanumeric understore symbolic [Alphanumeric][_][Symbolics]

  Precedence of user defined symbolic identifiers : determined by first character of Symbolic identifiers
  From Less to Higher
   all letters
   |
   ^
   &
   < >
   = !
   :
   + -
   * / %
   Other Special Chars
   */
  /*
a + b ^? c ^? d less a ==> b | c
(a + b) ^? c ^? d less a ==> b | c
(a + b) ^? c ^? d less (a ==> b) | c
((a + b) ^? c) ^? d less (a ==> b) | c
((((a + b) ^? c) ^? d) less ((a ==> b) | c))
*/

  val v = new Rational(1, 3)
  val w = -v //unary_-
}


class Rational(a: Int, b: Int) {
  require(b != 0, "Denominator must not be Zero")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  def nume: Int = a

  def deno: Int = b

  def less(that: Rational) = if (this.nume * that.deno < that.nume * that.deno) this else that

  def <(that: Rational) = this.nume * that.deno < that.nume * that.deno

  def >(that: Rational) = if (this < that) that else this

  //For unary operators, we need to Prefix them with unary_
  def unary_- = new Rational(-nume, deno)

  override def toString: String = {
    val g = gcd(nume, deno)
    s"${nume / g}/${deno / g}"
  }
}