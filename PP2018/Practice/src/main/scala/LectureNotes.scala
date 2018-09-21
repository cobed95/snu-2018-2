object LectureNotes {

  object TypeCheckingSafetyRule {
    {
      def f(x: Int) = x + a
      val x = f(10)
      val a = 10
      f(10)
    }
    // This is not allowed because not all names are not defined before the next "val" definition

    {
      def f(x: Int) = g(x)
      def g(x: Int) = 10
      val x = f(10)
      x
    }
    // This is allowed because all names in f are defined before the next "val" definition.

    {
      def f(x: Int) = g(x)
      val a = 10
      def g(x: Int) = 10
      f(10)
    }
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
}
