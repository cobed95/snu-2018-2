import scala.annotation.tailrec

object MidtermPrep {
  object BooleanExercise {
    def and(x: Boolean, y: Boolean): Boolean = if (!x) false else y

    def or(x: Boolean, y: Boolean): Boolean = if (x) true else y
  }

  object NewtonSqrt {
    def sqrt(x: Double): Double = {
      def sqrtIter(guess: Double, x: Double): Double =
        if (isGoodEnough(guess, x)) guess
        else sqrtIter(improve(guess, x), x)

      def improve(guess: Double, x: Double): Double =
        (guess + (x / guess)) / 2

      def isGoodEnough(guess: Double, x: Double): Boolean = {
        val guessSqr = guess * guess
        guessSqr / x < 1.01 && guessSqr / x > 0.99
      }

      sqrtIter(1.0, x)
    }
  }

  object TailRecursion {
    def sum(n: Int): Int =
      if (n <= 0) 0
      else n + sum(n - 1) // Not tailrecursed.

    def sumTailRec(n: Int): Int = {
      @tailrec def sumIter(result: Int, m: Int): Int =
        if (m <= 0) result
        else sumIter(result + m, m - 1)

      sumIter(0, n)
    }
  }

  object HigherOrderFunctions {
    def mapReduce(combine: (Int, Int)=>Int, iniVal: Int, f: Int=>Int, a: Int, b: Int): Int =
      if (a <= b) combine(f(a), mapReduce(combine, iniVal, f, a + 1, b))
      else iniVal

    def sum(f: Int=>Int, a: Int, b: Int): Int =
      mapReduce((x, y)=>x + y, 0, f, a, b)

    def product(f: Int=>Int, a: Int, b: Int): Int =
      mapReduce((x, y)=>x * y, 1, f, a, b)
  }

}
