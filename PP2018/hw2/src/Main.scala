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

  def zip(l1: IList, l2:IList): IList = ???

  /*
   Exercise 2: Exp calculator with sqrt
   Given Exp, calculate the result of Int value.
   For each case class EAdd/ESub/EMul, you may interpret them as
   normal integer operators: +, -, *.
   For each case class ESqr, you should calculate the square of
   the operand
   */

  def calculate(x : Exp) : Int = ???

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

  def sort[A](l: MyList[A])(cmp: A => A => Int): MyList[A] = ???
     
  /*
   Exercise 4: Tree traversal
   Implement preorder, inorder, postorder tree traversals.
   (https://en.wikipedia.org/wiki/Tree_traversal)
   */

  def preorder[A](tree: BTree[A]): MyList[A] = ???

  def inorder[A](tree: BTree[A]): MyList[A] = ???

  def postorder[A](tree: BTree[A]): MyList[A] = ???
}
