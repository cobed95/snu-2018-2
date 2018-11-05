package pp201802.hw3test
import pp201802.hw3.Main._
import pp201802.hw3.Data.DataBundle._
import reflect.runtime.universe._

object Test extends App {
  def print_result(b:Boolean) : Unit =
    if (b) println("PASS") else println("FAIL")

  // Problem 1
  {
    println("------Problem 1------");

    val mc = new Problem1.MyClass[Int, Int, Int, Int, Int, Int]()
    print_result(typeOf[mc.Ty1]<:<typeOf[mc.CommonTy]);
    print_result(typeOf[mc.Ty2]<:<typeOf[mc.CommonTy]);
    print_result(!(typeOf[Any]<:<typeOf[mc.CommonTy]));


  }
/*
  // Problem 2
  {
    println("------Problem 2------");

    val intEq: Int => Int => Boolean = { x => y => (x == y) }
    val d0 = Problem2.IterDictImpl.empty[Int, Int](intEq)
    val d1 = d0.add(1, 2)

    print_result(
      d1.find(1) match {
        case Some(n) => n == 2
        case _ => false
      }
    )
  }

  // Problem 3
  {
    println("------Problem 3------");

    val x = List("A", "B", "C")
    val bx = new Problem3.BiIterableList(x)

    print_result(
      bx.biIter.getValue match {
        case Some(x) => x == "A"
        case _ => false
      }
    )
  }

  // Problem 4
  {
    println("------Problem 4------");

    print_result(Problem4.primes.nth(5) == Some(13));
    print_result(Problem4.primes.nth(100) == Some(547));
  }

*/

}
