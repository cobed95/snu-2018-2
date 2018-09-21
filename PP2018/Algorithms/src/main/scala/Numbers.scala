object Numbers {
  def sqrt(x: Double): Double = {
    def sqrtIter(guess: Double, x: Double): Double = {
      def isGoodEnough(guess: Double, x: Double): Boolean = {
        val ratio = guess / x
        ratio >= 0.99 && ratio <= 1.01
      }

      def improve(guess: Double, x: Double): Double = {
        ((x / guess) + guess) / 2.0
      }

      if (isGoodEnough(guess, x)) guess
      else sqrtIter(improve(guess, x), x)
    }

    sqrtIter(1, x)
  }

  def power(base: Double, exponent: Int): Double = {
    if (exponent == 0) 1
    else if (exponent % 2 == 0) power(base, exponent / 2) * power(base, exponent / 2)
    else power(base, exponent / 2) * power(base, exponent / 2) * base
  }

  def gcd(x: Int, y: Int): Int = {
    if (y == 0) x else gcd(y, x % y)
  }

  def lcm(x: Int, y: Int): Int = x * y / gcd(x, y)

  def pascalTriangle(n: Int): Unit = {
    def pascal(i: Int, j: Int): Int = {
      if (j == 0 || i == j) 1
      else pascal(i - 1, j - 1) + pascal(i - 1, j)
    }

    def pascalTriangleIter(n: Int, m: Int): Unit = {
      if (m >= n) print("\n")
      else {
        for (k <- 0 to m) print(pascal(m, k) + " ")
        print("\n")
        pascalTriangleIter(n, m + 1)
      }
    }

    pascalTriangleIter(n, 0)
  }
}
