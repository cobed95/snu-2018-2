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

  object AbstractClasses {
    abstract class BadPrimesSig {
      def create : BadPrimesSig
      def prime : Int
      def getNext : BadPrimesSig
    }
    // Because create is a member of PrimesSig, you cannot create PrimesSig
    // without a PrimesSig. This is not a good design.

    abstract class PrimesSig[P] {
      def create : P
      def prime(p: P) : Int
      def getNext(p: P) : P
    }
    // This is a better design because we can introduce P with create(),
    // and eliminate P with prime() or getNext()
    // This is a type class. It gives specifications of type.
    // If you use type classes you never use object.method()
    // P is only some data.
    // We can create many interfaces like this to specify what P is.
    // This is essentially different from OOP.
    // "I have a concrete type P, please register it as a member of PrimesSig."

    def nthPrime[P](tc: PrimesSig[P], n: Int): Int = {
      def go(p: P, k: Int): Int =
        if (k <= 1) tc.prime(p)
        else go(tc.getNext(p), k - 1)
      if (n == 0) 2 else go(tc.create, n)
    }
    // Much easier to read, and easier to get compiler support.
    // This is a much more natural form.

    class Primes private (val prime: Int, protected val primes: List[Int]) {
      def this() = this(3, List(3))
      def getNext: Primes = {
        val p = computeNextPrime(prime + 2)
        new Primes(p, primes ++ (p :: Nil))
      }
      private def computeNextPrime(n : Int): Int =
        if (primes.forall((p: Int) => n % p != 0)) n
        else computeNextPrime(n + 2)
    }

    class PrimesImpl extends PrimesSig[Primes] {
      val create = new Primes
      def prime(p: Primes) = p.prime
      def getNext(p: Primes) = p.getNext
    }

    val pi = new PrimesImpl
    // This is a OOP style hack.
    // Three functions for data. (record)
    // pi is a record.

    nthPrime[Primes](pi, 10000)
    //Primes can be omitted, because it can be inferred.
  }

  object Traits {
    /*
    abstract class A {
      def a : Int
    }

    trait B {
      def a : String
    }

    abstract class C extends A with B
    */
    // This is not allowed because A.a and B.a have conflicting return types.

    class A(val a : Int) {
      def this() = this(0)
    }
    trait B {
      def f(x: Int): Int = x
    }
    trait C extends A with B {
      def g(x: Int): Int = x + a
    }
    trait D extends B {
      def h(x: Int): Int = f(x + 50)
    }
    class E extends A(10) with C with D {
      override def f(x: Int) = x * a
    }
    val e = new E
  }

  object StackingWithTraits {
    // Base (What is pizza?)
    trait IntStack {
      def get(): (Int, IntStack)
      def put(x: Int): IntStack
    }

    // Core (Let's make doughs and stuff)
    class BasicIntStack protected (xs: List[Int]) extends IntStack {
      override val toString = "Stack: " + xs.toString
      def this() = this(Nil)
      protected def mkStack(xs: List[Int]): IntStack =
        new BasicIntStack(xs)
      // If we don't do this, we can't override it in DIFIntStack.
      // Then we don't get a whole pizza, but after adding one topping,
      // you end up going back to the dough: BasicIntStack.
      def get(): (Int, IntStack) = (xs.head, mkStack(xs.tail))
      def put(x: Int): IntStack = mkStack(x :: xs)
    }

    // Custom (Toppings!)
    trait Doubling extends IntStack {
      abstract override def put(x: Int): IntStack = super.put(2 * x)
    }

    trait Incrementing extends IntStack {
      abstract override def put(x: Int): IntStack = super.put(x + 1)
    }

    trait Filtering extends IntStack {
      abstract override def put(x: Int): IntStack =
        if (x >= 0) super.put(x) else this // If x < 0, ignore.
    }

    // Now let's cook.
    class DIFIntStack protected (xs: List[Int])
      extends BasicIntStack(xs)
      with Doubling with Incrementing with Filtering
    {
      def this() = this(Nil)
      override def mkStack(xs: List[Int]): IntStack =
        new DIFIntStack(xs)
    }

    object Test {
      val s0 = new DIFIntStack
      val s1 = s0.put(3)
      val s2 = s1.put(-2)
      val s3 = s2.put(4)
      val (v1, s4) = s3.get()
      val (v2, s5) = s4.get()
      // put(x) = add x
      // put(x) = add(2 * x)
      // put(x) = add(2 * (x + 1))
      // put9x) = if x >= 0 then add(2 * (x + 1)) else this.
      // Changing the order of extension will change results.
      println("s0: " + s0)
      println("s1: " + s1)
      println("s2: " + s2)
      println("s3: " + s3)
      println("s4: " + s4)
      println("s5: " + s5)
      println("v1: " + v1)
      println("v2: " + v2)
    }
  }
}
