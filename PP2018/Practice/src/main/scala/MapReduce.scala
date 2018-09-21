import scala.annotation.tailrec

object MapReduce {
  def mapReduce(reduce: (Int, Int)=>Int, initVal: Int, f: Int=>Int, a: Int, b: Int): Int = {
    @tailrec def mapReduceIter(result: Int, reduce: (Int, Int)=>Int, initVal: Int, f: Int=>Int, a: Int, b: Int): Int =
      if (a == b) result else mapReduceIter(reduce(f(a), result), reduce, initVal, f, a + 1, b)

    mapReduceIter(initVal, reduce, initVal, f, a, b)
  }

  def sum(f: Int=>Int, a: Int, b: Int): Int =
    mapReduce((x, y)=>x + y, 0, f, a, b)

  def product(f: Int=>Int, a: Int, b: Int): Int =
    mapReduce((x, y)=>x * y, 1, f, a, b)

  // Below is curried.

  def mapReduceCurry(reduce: (Int, Int)=>Int, initVal: Int)(f: Int=>Int)(a: Int, b: Int): Int = {
    if (a <= b) reduce(f(a), mapReduceCurry(reduce, initVal)(f)(a + 1, b)) else initVal
  }
}
