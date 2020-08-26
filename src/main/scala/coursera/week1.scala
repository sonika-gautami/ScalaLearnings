package coursera

object week1 extends App {

  /* How a method is evaluated:
     Given def sq(x: Int) = x*x & def sumOfSq(a: Int, b:Int) = sq(a) + sq(b)
      sumOfSq(2 , 3+1)
      sumOfSq(2 , 4)
      sq(2) + sq(4)
      4 + sq(4)
      4 + 16
      20
    named as Substitution Model
    - reduce expression to Value
   */

  /*
  Above is  the example of 'call by value'
  -> Pro: argument evaluated only once.

  Another is 'call by name'
    sumOfSq(2 , 3+1)
    sq(2) + sq(3+1)
    4 + sq(3+1)
    4 + (3+1)*(3+1)
    4 + 4*4
    4 + 16
    20
    -> Pro: argument not evaluated until used in evaluation body


call by value -> def sum(x: Int, y: Int)
call by name  -> def sum(x: Int, y: => Int)   '=>' before type
   'def' = 'by name'
   'val' = 'by value'

    test(2, 3)
    2*3
    6

    test(3+4, 8)
    test(7, 8) #this is not part of step
    7*8
    56

    (3+4) * 8
    7*8
    56

    test(7, 2*4)
    test(7, 8)
    7*8
    56

    7*2*4
    56


    test(3+4, 2*4)
    test(7, 8)
    7*8
    56

    (3+4) *2*4
    7*2*4
    56

    def and(x,y) = x && y
    def and(x: Boolean, y: => Boolean) = if(x) y else x  'call by name' for second param !

    def or(x,y) == x || y
    def or(x: Boolean, y: => Boolean) = if(x) true else y
   */


  def and(x: Boolean, y: => Boolean) = if (x) y else true //'call by name' for second param !

  def or(x: Boolean, y: => Boolean) = if (x) true else y

  def sqrt(x: Double): Double = ???
  /*
   one is by substituting successive approximation.
   Given x to find sqrt of:
   1] take y (initial value)
   2] mean of y & x/y
   */

  //Recursive functions in scala needs -> explicit return type

  //'name space pollution' -> sub functions used in function inside the function body

}
