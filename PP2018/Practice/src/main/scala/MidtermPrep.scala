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

  object Currying {
    def mapReduce(combine: (Int, Int)=>Int, iniVal: Int)(f: Int=>Int)(a: Int, b: Int): Int = {
      if (a <= b) combine(f(a), mapReduce(combine, iniVal)(f)(a + 1, b))
      else iniVal
    }

    def sum = mapReduce((x, y)=>x + y, 0) _
    val product = mapReduce((x, y)=>x * y, 1) _
  }

  object Exceptions {
    class factRangeException(val arg: Int) extends Exception

    def fact(n: Int): Int =
      if (n < 0) throw new factRangeException(n)
      else if (n == 0) 1
      else n * fact(n - 1)

    try {
      println(fact(3))
      println(fact(-2))
    } catch {
      case e : factRangeException => {
        println("fact range error: " + e.arg)
      }
    }
  }

  object StructuralTypes {
    val gn = 0
    object foo {
      val a = 3
      def b = a + 1
      def f(x: Int) = b + x + gn
    }

    type Foo = {val a: Int; def b: Int; def f(x: Int): Int}
    def g(x: Foo) = {
      val gn = 10
      x.f(3)
    }

    g(foo)
  }

  object AlgebraicDataTypes {
    sealed abstract class IList[T]
    case class INil[T]() extends IList[T]
    case class ICons[T](head: T, tail: IList[T]) extends IList[T]

    sealed abstract class IOption[T]
    case class INone[T]() extends IOption[T]
    case class ISome[T](value: T) extends IOption[T]

    sealed abstract class BTree[T]
    case class Leaf[T]() extends BTree[T]
    case class Node[T](value: T, left: BTree[T], right: BTree[T]) extends BTree[T]

    sealed abstract class GTree[T]
    case class GLeaf[T]() extends BTree[T]
    case class GNode[T](value: T, leftMostChild: GTree[T], rightSibling: GTree[T]) extends GTree[T]

    def find[T](t: BTree[T], x: T): Boolean =
      t match {
        case Leaf() => false
        case Node(n, _, _) if n == x => true
        case Node(_, left, right) =>
          find(left, x) || find(right, x)
      }

    type BSTree[T] = BTree[(Int, T)]

    def lookup[T](t: BSTree[T], key: Int): IOption[T] =
      t match {
        case Leaf() => None()
        case Node((k, v), left, right) =>
          k match {
            case _ if key == k => ISome(v)
            case _ if key < k => lookup(left, key)
            case _ => lookup(right, key)
          }
      }
  }

}
