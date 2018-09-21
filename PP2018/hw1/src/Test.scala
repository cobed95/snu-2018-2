package pp201802.hw1test
import pp201802.hw1.Main._

object Test extends App {
  def print_result(b:Boolean) : Unit =
    if (b) println("O") else println("X")

  // Problem 1
  print_result(fib3A(5) == 9)
  print_result(fib3B(5) == 9)
  print_result(fib3C(5) == 9)


  // Problem 2
  print_result(prime_check(7L) == true)
  print_result(prime_check(8L) == false)

  // Problem 3
  print_result(repeat((x: Int) => x + 2)(5)(3) == 11)
  print_result(power(3)(3) == 27)
}
