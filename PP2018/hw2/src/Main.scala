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

  def zip(l1: IList, l2:IList): IList = {
    (l1, l2) match {
      case (INil(), INil()) => INil()
      case (l1, INil()) => l1
      case (INil(), l2) => l2
      case (ICons(l1Head, l1Tail), l2) =>
        ICons(l1Head, zip(l2, l1Tail))
    }
  }

  /*
   Exercise 2: Exp calculator with sqrt
   Given Exp, calculate the result of Int value.
   For each case class EAdd/ESub/EMul, you may interpret them as
   normal integer operators: +, -, *.
   For each case class ESqr, you should calculate the square of
   the operand
   */

  def calculate(x : Exp) : Int = {
    x match {
      case EInt(value) => value
      case EAdd(lhs, rhs) => calculate(lhs) + calculate(rhs)
      case ESub(lhs, rhs) => calculate(lhs) - calculate(rhs)
      case EMul(lhs, rhs) => calculate(lhs) * calculate(rhs)
      case ESqr(e) => calculate(e) * calculate(e)
    }
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

    def sortIter[A](left: MyList[A], right: MyList[A])(cmp: A => A => Int): MyList[A] = {

      def switch[A](l: MyList[A])(cmp: A => A => Int): (MyList[A], A) = {
        l match {
          case MyCons(first, MyNil()) => (MyNil(), first)
          case MyCons(first, MyCons(second, tail)) if cmp(first)(second) > 0 => {
            val newTail = MyCons(first, tail)
            val pair = switch(newTail)(cmp)
            val cutTail = pair._1
            val max = pair._2
            (MyCons(second, cutTail), max)
          }
          case MyCons(first, tail) => {
            val pair = switch(tail)(cmp)
            val cutTail = pair._1
            val max = pair._2
            (MyCons(first, cutTail), max)
          }
        }
      }

      (left, right) match {
        case (MyNil(), right) => right
        case (left, right) => {
          val pair = switch(left)(cmp)
          val newLeft = pair._1
          val newRight = MyCons(pair._2, right)
          sortIter(newLeft, newRight)(cmp)
        }
      }
    }

    sortIter(l, MyNil())(cmp)
  }
     
  /*
   Exercise 4: Tree traversal
   Implement preorder, inorder, postorder tree traversals.
   (https://en.wikipedia.org/wiki/Tree_traversal)
   */

  def preorder[A](tree: BTree[A]): MyList[A] = {
    tree match {
      case Leaf() => MyNil()
      case Node(value, left, right) => MyCons(value, concat(preorder(left), preorder(right)))
    }
  }

  def inorder[A](tree: BTree[A]): MyList[A] = {
    tree match {
      case Leaf() => MyNil()
      case Node(value, left, right) => concat(inorder(left), MyCons(value, inorder(right)))
    }
  }

  def postorder[A](tree: BTree[A]): MyList[A] = {
    tree match {
      case Leaf() => MyNil()
      case Node(value, left, right) => concat(postorder(left), add(postorder(right), value))
    }
  }

  def concat[A](left: MyList[A], right: MyList[A]): MyList[A] = {
    (left, right) match {
      case (MyNil(), MyNil()) => MyNil()
      case (left, MyNil()) => left
      case (MyNil(), right) => right
      case (left, MyCons(head, tail)) =>
        concat(add(left, head), tail)
    }
  }

  def add[A](list: MyList[A], element: A): MyList[A] = {
    list match {
      case MyNil() => MyCons(element, MyNil())
      case MyCons(head, tail) => MyCons(head, add(tail, element))
    }
  }
}
