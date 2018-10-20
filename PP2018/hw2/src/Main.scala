package pp201802.hw2
import pp201802.hw2.Data._
import pp201802.hw2.Data.DataBundle._

object Main {
  /*
   Implement given functions, which is currently blank. (???)
   */

  /*
   Exercise 1: Zip up two lists
   Zips up two lists into one, alternating between elements
   taken from the first list and elements from the second.
   ex)
   zip of {3, 2, 1} and {4, 5} is equal to {3, 4, 2, 5, 1}.
   zip of {1, 8} and {9, 2, 4, 7} is eqaul to {1, 9, 8, 2, 4, 7}.  
   */

  def zip(l1: IList, l2:IList): IList =
    (l1, l2) match {
      case (INil(), INil()) => INil()
      case (l1, INil()) => l1
      case (INil(), l2) => l2
      case (ICons(l1Head, l1Tail), l2) =>
        ICons(l1Head, zip(l2, l1Tail))
    }

  /*
   Exercise 2: Exp calculator with sqrt
   Given Exp, calculate the result of Int value.
   For each case class EAdd/ESub/EMul, you may interpret them as
   normal integer operators: +, -, *.
   For each case class ESqr, you should calculate the square of
   the operand
   */

  def calculate(x : Exp) : Int =
    x match {
      case EInt(i) => i
      case EAdd(lhs, rhs) => calculate(lhs) + calculate(rhs)
      case ESub(lhs, rhs) => calculate(lhs) - calculate(rhs)
      case EMul(lhs, rhs) => calculate(lhs) * calculate(rhs)
      case ESqr(e) =>
        val calculated = calculate(e)
        calculated * calculated
    }

  /*
   Exercise 3: Bubble sort with Polymorphism
   Implement the bubble sort (https://en.wikipedia.org/wiki/Bubble_sort).
   The output should be a reordering of the input in nondecreasing order.

   Each time, comparator(cmp) will be given as an argument.
   The result of cmp(k1, k2) should be interpreted as the following:
     negative: k1 < k2
     zero:     k1 == k2
     positive: k1 > k2
   */

  def sort[A](l: MyList[A])(cmp: A => A => Int): MyList[A] = {
    def sortIter(left: MyList[A], right: MyList[A]): MyList[A] = {
      (left, right) match {
        case (MyNil(), MyNil()) => MyNil()
        case (MyNil(), right) => right
        case (left, right) =>
          val removedMax = removeMax(left)
          sortIter(removedMax._1, MyCons(removedMax._2, right))
      }
    }

    def removeMax(list: MyList[A]): (MyList[A], A) = {
      list match {
        case MyCons(last, MyNil()) => (MyNil(), last)
        case MyCons(first, MyCons(second, tail)) if cmp(first)(second) > 0 =>
          val pair: (MyList[A], A) = removeMax(MyCons(first, tail))
          (MyCons(second, pair._1), pair._2)
        case MyCons(first, tail) =>
          val pair: (MyList[A], A) = removeMax(tail)
          (MyCons(first, pair._1), pair._2)
      }
    }

    sortIter(l, MyNil())
  }
     
  /*
   Exercise 4: Tree traversal
   Implement preorder, inorder, postorder tree traversals.
   (https://en.wikipedia.org/wiki/Tree_traversal)
   */

  def preorder[A](tree: BTree[A]): MyList[A] =
    tree match {
      case Leaf() => MyNil()
      case Node(value, left, right) =>
        MyCons(value, concat(preorder(left), preorder(right)))
    }

  def inorder[A](tree: BTree[A]): MyList[A] =
    tree match {
      case Leaf() => MyNil()
      case Node(value, left, right) =>
        concat(inorder(left), MyCons(value, inorder(right)))
    }

  def postorder[A](tree: BTree[A]): MyList[A] =
    tree match {
      case Leaf() => MyNil()
      case Node(value, left, right) =>
        concat(postorder(left), concat(postorder(right), MyCons(value, MyNil())))
    }

  def concat[A](left: MyList[A], right: MyList[A]): MyList[A] =
    (left, right) match {
      case (MyNil(), MyNil()) => MyNil()
      case (MyCons(_, _), MyNil()) => left
      case (MyNil(), MyCons(rightHead, rightTail)) =>
        MyCons(rightHead, concat(left, rightTail))
      case (MyCons(leftHead, leftTail), MyCons(_, _)) =>
        MyCons(leftHead, concat(leftTail, right))
    }
}
