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

    val d2 = d1.add(2, 3)
    val d3 = d2.add(3, 4)
    print_result(Problem2.sumElements(d3) == 9)

    val d4 = d3.add(1, 3)
    print_result(Problem2.sumElements(d4) == 10)
  }

  // Problem 3
  {
    println("------Problem 3------")

    println("-----BiIterableList-----")

    val x = List("A", "B", "C")
    val bx = new Problem3.BiIterableList(x)
    print_result(
      bx.biIter.getValue match {
        case Some(x) => x == "A"
        case _ => false
      }
    )
    val bx2 = bx.biIter.getPrev
    print_result(
      bx2.getValue match {
        case None => true
        case _ => false
      }
    )

    val bx3 = bx2.getPrev.getNext
    print_result(
      bx3.getValue match {
        case Some(x) => x == "A"
        case _ => false
      }
    )

    val bx4 = bx3.getNext.getNext
    print_result(
      bx4.getValue match {
        case Some(x) => x == "C"
        case _ => false
      }
    )

    val bx5 = bx4.getNext
    print_result(
      bx5.getValue match {
        case None => true
        case _ => false
      }
    )

    val bx6 = bx5.getNext.getPrev
    print_result(
      bx6.getValue match {
        case Some(x) => x == "C"
        case _ => false
      }
    )

    val emptyList = Nil
    val biIterEmptyList = new Problem3.BiIterableList(Nil)
    print_result(biIterEmptyList.biIter.getValue == None)

    val bel2 = biIterEmptyList.biIter.getNext
    print_result(bel2.getValue == None)

    println("-----BiIterableTree-----")

    val biTree = new Problem3.Node(1,
      Problem3.Node(2,
        Problem3.Node(3,
          Problem3.Empty(),
          Problem3.Empty()),
        Problem3.Node(4,
          Problem3.Empty(),
          Problem3.Empty())),
      Problem3.Node(5,
        Problem3.Node(6,
          Problem3.Empty(),
          Problem3.Empty()),
        Problem3.Node(7,
          Problem3.Empty(),
          Problem3.Empty())))

    val biTreeIter0 = biTree.biIter
    print_result(
      biTreeIter0.getValue match {
        case Some(x) => x == 1
        case _ => false
      }
    )
    println(biTreeIter0.getValue)

    val biTreeIter1 = biTreeIter0.getNext
    print_result(
      biTreeIter1.getValue match {
        case Some(x) => x == 2
        case _ => false
      }
    )
    println(biTreeIter1.getValue)
    println(biTreeIter1.getNext.getValue)

    val biTreeIter2 = biTreeIter1.getPrev
    print_result(
      biTreeIter2.getValue match {
        case Some(x) => x == 1
        case _ => false
      }
    )

    val biTreeIter3 = biTreeIter2.getPrev
    print_result(biTreeIter3.getValue == None)

    val biTreeIter4 = biTreeIter3.getNext
    print_result(
      biTreeIter4.getValue match {
        case Some(x) => x == 1
        case _ => false
      }
    )

  }
/*
  // Problem 4
  {
    println("------Problem 4------");

    print_result(Problem4.primes.nth(5) == Some(13));
    print_result(Problem4.primes.nth(100) == Some(547));
  }

*/

}
