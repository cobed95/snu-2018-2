package pp201802.hw1
import scala.annotation.tailrec

object Main {
  /*
   Implement given functions, which is currently blank. (???)
   */

  /*
   Exercise 1: Fibonacci3

   A) We are going to define a Fibonacci3 sequence, a variation of the Fibonacci sequence
   (See https://en.wikipedia.org/wiki/Fibonacci_number).
   Each number in the Fibonacci3 sequence is a sum of three preceding ones.
   The sequence of Fib3 is "1, 1, 1, 3, 5, 9, 17, 31, 57, 105, 193, 355, ... ".
   Given n, calculate the n'th number in the Fibonacci3 sequence.
   Having exponential time complexity is OK for this problem.
   For any input n s.t. 0 <= n <= 20, your program should terminate within 1 seconds.
   */
  def fib3A(n: Int): Int = {
    if (n == 0 || n == 1 || n == 2) 1
    else fib3A(n - 1) + fib3A(n - 2) + fib3A(n - 3)
  }

  /*
   B) Same as A), but you should implement a faster version avoiding the exponential time complexity.
   For any input n s.t. 0 <= n <= 10^3, your program should terminate within 1 seconds.
   */
  def fib3B(n: Int): BigInt = {
    /*
     This skeleton code is just for hint. You may implement this problem in your own way.
     n := index for Fibonacci3 number, n >= 3
     output := (n-2)'th Fibonacci3 number, (n-1)'th Fibonacci3 number and n'th Fibonacci3 number
     */
    /*
     You can implement remaining parts without knowledge of pair type.
     If you want to know more about pair/tuple, search "scala tuple" on google.
     In short, you can build tuple with "(_, _, _, .. _,)" syntax,
     and get n'th element with "._n".
     */
    def _fib3B(n: Int): (BigInt, BigInt, BigInt) = {
      if (n < 3) (1, 1, 1)
      else {
        val (past3, past2, past1) = _fib3B(n-1)
        val current = past3 + past2 + past1
        (past2, past1, current)
      }
    }
    _fib3B(n)._3
  }

  /*
   C) Same as B), but you should implement it with tail recursion.
   By using tail recursion, your algorithm will not waste the stack space.
   For any input n s.t. 0 <= n <= 10^5, your program should terminate within 5 seconds.
   */

  def fib3C(n: Int): BigInt = {
    /*
     This skeleton code is just for hint. You may implement this problem in your own way.
     idx := current index
     current := Fibonacci3 number for idx.
     past1 := Fibonacci3 number for idx-1.
     past2 := Fibonacci3 number for idx-2.
     output := n'th Fibonacci number
     */
    @tailrec
    def _fib3C(idx: Int, current: BigInt, past1: BigInt, past2: BigInt): BigInt = {
      if (idx == n) current
      else {
        _fib3C(idx + 1, current + past1 + past2, current, past1)
      }
    }

    if (n == 0 || n == 1 || n == 2) 1
    else _fib3C(3, 3, 1, 1)
  }

  /*
   Exercise 2: Prime Checker

   Implement the function "prime_check" which returns true if the input is a prime number and false otherwise. 
   You can use the given sqrt function.
   For any input number n s.t. 1 <= n <= 10^10, your program should terminate within 5 seconds.
   You should implement it using a tail recursive function.
   (Hint: You don’t need to check that the input number is indivisible by all natural numbers smaller than the input.
   You only need to check that the input is indivisible by all (odd) natural numbers up to sqrt(input))
   */

  def sqrt(x: Double) = {
    def sqrtIter(guess: Double, x: Double): Double = {
      if (isGoodEnough(guess,x)) guess
      else sqrtIter(improve(guess,x),x)
    }
    def isGoodEnough(guess: Double, x: Double) = {
      val diff = guess * guess - x
      diff > -1 && diff < 1
    }
    def improve(guess: Double, x: Double) =
      (guess + x/guess) / 2
    sqrtIter(1, x)
  }

  def prime_check(n : Long) : Boolean = {
    @tailrec
    def _prime_check(n: Long, divisor: Long): Boolean = {
      if (n % divisor == 0) false
      else if (divisor > sqrt(n)) true
      else _prime_check(n, divisor + 1)
    }

    if (n % 2 == 0) false
    else _prime_check(n, 3)
  }

  /*
   Exercise 3: Repeat & power
   A) Implement a function "repeat".
   The function takes a function f and two integers "init" and "count" as arguments, and returns a value obtained by applying f to "init" "count" times.
   (e.g. repeat ((x: Int) => x + 2)(5)(3) results in 5+2+2+2 = 11)
   */

  def repeat (f: Int => Int)(init: Int)(num: Int): Int =
    if (num == 0) init
    else repeat(f)(f(init))(num - 1)


  /*
   B) You can implement useful functions using "repeat".
   Write a function "power", which computes exponentiation.
   (i.e. power (n)(m) results in n^m)
   */

  def power (n: Int)(m: Int) = {
    if (m % 2 == 0) repeat ((x: Int) => x * x)(n)(m / 2)
    else n * repeat((x: Int) => x * x)(n)(m / 2)
  }
}

