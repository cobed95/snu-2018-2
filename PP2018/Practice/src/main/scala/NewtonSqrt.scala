import scala.annotation.tailrec

object NewtonSqrt {
  def sqrt(x: Double) = {
    @tailrec def sqrtIter(guess: Double, x: Double): Double = {
      def isGoodEnough(guess: Double, x: Double) = {
        val ratio = guess * guess / x
        ratio > 0.999 && ratio < 1.001
      }

      def improve(guess: Double, x: Double) =
        (guess + x / guess) / 2

      if (isGoodEnough(guess, x)) guess
      else sqrtIter(improve(guess, x), x)
    }

    sqrtIter(1, x)
  }
}
