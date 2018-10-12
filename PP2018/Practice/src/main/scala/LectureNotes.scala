object LectureNotes {

  object TypeCheckingSafetyRule {
    /*
    {
      def f(x: Int) = x + a
      val x = f(10)
      val a = 10
      f(10)
    }
    */
    // This is not allowed because not all names are not defined before the next "val" definition

    {
      def f(x: Int) = g(x)
      def g(x: Int) = 10
      val x = f(10)
      x
    }
    // This is allowed because all names in f are defined before the next "val" definition.

    /*
    {
      def f(x: Int) = g(x)
      val a = 10
      def g(x: Int) = 10
      f(10)
    }
    */
    // This is not allowed because g(x) is defined after a "val" definition.
    // The compiler assumes that computation ("val") may occur before definition. So it does not allow this.

    // This rule is because "val" definition is the only place where something is possibly computed.
    // High level idea is that every definition should be defined before any computation
  }

  object Currying {
    def sumNonCurried(f: Int=>Int, a: Int, b: Int): Int =
      if (a <= b) f(a) + sumNonCurried(f, a + 1, b) else 0
    def linear(n: Int) = n
    def square(n: Int) = n * n
    def cube(n: Int) = n * n * n
    def sumLinear(a: Int, b: Int) = sumNonCurried(linear, a, b)

    def sumCurried(f: Int=>Int): (Int, Int)=>Int = {
      def sumF(a: Int, b: Int): Int =
        if (a <= b) f(a) + sumF(a + 1, b) else 0
      sumF _
    }
    // Here the closure is created just once.
    sumNonCurried(linear, 1, 10)
    sumCurried(linear)(1, 10)

    // Uncurried version is slightly faster, but computationally they are equivalent.
    // But if we curry, we don't have to define sumLinear()
    // Above code is equivalent to...

    def sumCurried1(f: Int=>Int): (Int, Int)=>Int =
      (a, b) => if (a <= b) f(a) + sumCurried1(f)(a + 1, b) else 0
    // Here, anonymous function is used to return a function.
    // If naively implemented like above, a closure is created every recursive call.
    // But because this is a common pattern, it is highly possible that the compiler
    // optimizes this so that closure is created just once.
    def sumCurried2(f: Int=>Int)(a: Int, b: Int): Int =
      if (a <= b) f(a) + sumCurried2(f)(a + 1, b) else 0
    // sumCurried2 is the simplest and the standard way of writing curried functions.
  }

  object Tuples {
    val a = (1, 2, 3): (Int, Int, Int)
    println(a._1) // prints 1.
  }

  object StructuralTypes {
    val foo = new {
      val a = 3
      def b = a + 1
      def f(x: Int) = b + x
      def f(x: String) = "hello" + x
    }
    // Above is equal to...
    object bar {
      val a = 3
      def b = a + 1
      def f(x: Int) = b + x
      def f(x: String) = "hello" + x
    }
    // So apparently, val new = object

    def foo(x: Int) = x + 1
    object record {
      val g: Int=>Int = foo _
      def f(x: Int) = x + 1
    }
    // val g can store function codes that is defined where-ever.
    // Functions inside record types can be defined because record types are values, and consequently closures.
  }

  object AlgebraicDatatypes {
    sealed abstract class Attr
    case class Name(name: String) extends Attr
    case class Age(age: Int) extends Attr
    case class DOB(year: Int, month: Int, day: Int) extends Attr
    //This allows access by name, not order, just like structural types.
    case class Height(height: Double) extends Attr

    val a: Attr = Name("Chulsoo Kim")
    val b: Attr = DOB(2000,3,10)

    sealed abstract class IList
    case class INil() extends IList
    case class ICons(hd: Int, tl: IList) extends IList

    val x: IList = ICons(2, ICons(1, INil()))
    def gen(n: Int): IList = {
      if (n <= 0) INil()
      else ICons(n, gen(n - 1))
    }
    sealed abstract class IOption
    case class INone() extends IOption
    case class ISome(data: Int) extends IOption

    sealed abstract class BTree
    case class Leaf() extends BTree
    case class Node(data: Int, left: BTree, right: BTree) extends BTree
  }

  object IList {
    sealed abstract class IOption
    case class INone() extends IOption
    case class ISome(x: Int) extends IOption

    sealed abstract class IList
    case class INil() extends IList
    case class ICons(hd: Int, tl: IList) extends IList

    def length(xs: IList): Int =
      xs match {
        case INil() => 0
        case ICons(x, tl) => 1 + length(tl)
      }

    def secondElmt(xs: IList): IOption =
    xs match {
      case INil() | ICons(_, INil()) => INone()
      case ICons(_, ICons(x, _)) => ISome(x)
      case _ => INone()
    }
  }

  object BinaryTree {
    sealed abstract class BTree
    case class Leaf() extends BTree
    case class Node(value: Int, left: BTree, right: BTree) extends BTree

    def find(t: BTree, x: Int): Boolean =
      t match {
        case Leaf() => false
        case Node(n, lt, rt) =>
          if (x == n) true
          else find(lt, x) || find(rt, x)
      }
    def findSubTree(t: BTree, x: Int): Option[BTree] =
      t match {
        case Leaf() => None
        case Node(n, lt, rt) =>
          if (x == n) Some(t)
          else
            findSubTree(lt, x) match {
              case None => findSubTree(rt, x)
              case res => res
            }
      }
  }

  object ParametricPolymorphism {
    def id[A](x: A): A = x
    val f = id _

    // Type parameters are only allowed for def's.
    // id[T] _ is a value of ype T=>T
    // This is because there are many challenges to technically implementing
    // type checking and type inference with fully general polymorphism.
    // A solution people found was: val f = (def g[A](A->A); g _)

    id[Int](3) // This is what its supposed to look like.
    id(3) // But this is allowed through type inference.

    def applyn[A](f: A => A, n: Int, x: A): A =
      n match {
        case 0=>x
        case _ => f(applyn(f, n - 1, x))
      }

    applyn((x:Int)=>x+1,100,3)
    applyn((x:String)=>x+"!", 10, "gil")
    applyn(id[String], 10, "hur")

    def foo[A,B](f: A=>A, x: (A,B)) : (A,B) =
      (applyn[A](f, 10, x._1), x._2)
    foo[String,Int]((x:String)=>x+"!",("abc",10))
  }
}
