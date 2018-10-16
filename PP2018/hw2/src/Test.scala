package pp201802.hw2test
import pp201802.hw2.Main._
import pp201802.hw2.Data._
import pp201802.hw2.Data.DataBundle._

object Test extends App {
  def print_result(b:Boolean) : Unit =
    if (b) println("O") else println("X")

  def listIntToIList(xs: List[Int]): IList =
    xs match {
      case (h :: t) => ICons(h, listIntToIList(t))
      case Nil => INil()
    }
    
  def listToMyList[A](xs: List[A]): MyList[A] =
    xs match {
      case (h :: t) => MyCons(h, listToMyList(t))
      case Nil => MyNil()
    }
    
  // Problem 1
  {
    val a = listIntToIList(List(1,3,5))
    val b = listIntToIList(List(2,4,6,7,8))
    val c = listIntToIList(List(1,2,3,4,5,6,7,8))

    print_result(zip(a,b) == c)
  }

  // Problem 2
  {
    val three = EInt(3)
    val two = EInt(2)
    
    print_result(calculate(EAdd(two, three)) == 5)
  }

  // Problem 3
  {
    val cmp = (scala.math.Ordering.Int.compare _).curried
    val a : MyList[Int] = listToMyList(List(3,2,1))
    val b : MyList[Int] = listToMyList(List(1,2,3))

    print_result(sort(a)(cmp) == b)
  }
  

  // Problem 4
  {
    val LeafI = Leaf[Int]()
    val a = Node(1, Node(2, LeafI, Node(3, LeafI, LeafI)), Node(4, LeafI, Node(5, LeafI, LeafI)))
    val b = listToMyList(List(1,2,3,4,5))
    val c = listToMyList(List(2,3,1,4,5))
    val d = listToMyList(List(3,2,5,4,1))

    print_result(preorder(a) == b)
    print_result(inorder(a) == c)
    print_result(postorder(a) == d)    
  }
}
