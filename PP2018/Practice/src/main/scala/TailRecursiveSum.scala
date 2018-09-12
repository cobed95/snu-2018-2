import scala.annotation.tailrec

object TailRecursiveSum {
  def sumDown(n: Int): Int = {
    @tailrec def sumIter(result: Int, m: Int): Int = {
      if (m <= 0) result else sumIter(m + result, m - 1)
    }

    sumIter(0, n)
  }

  def sumUp(n: Int): Int = {
    @tailrec def sumIter(result: Int, m: Int, i: Int): Int = {
      if (i > m) result else sumIter(i + result, m, i + 1)
    }

    sumIter(0, n, 0)
  }
}
