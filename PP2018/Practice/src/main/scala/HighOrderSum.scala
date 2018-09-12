import scala.annotation.tailrec

object HighOrderSum {
  def sum(f: Int=>Int, n: Int): Int = {
    @tailrec def sumIter(result: Int, f: Int=>Int, m: Int): Int =
      if (m <= 0) result else sumIter(result + f(m), f, m - 1)

    sumIter(0, f, n)
  }

  def sumLinear(n: Int): Int = sum(x=>x, n)
  def sumSquare(n: Int): Int = sum(x=>x * x, n)
  def sumCube(n: Int): Int = sum(x=>x * x * x, n)
}
