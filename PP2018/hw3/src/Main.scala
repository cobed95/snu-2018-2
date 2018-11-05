package pp201802.hw3
import pp201802.hw3.Data.DataBundle._
import scala.annotation.tailrec

object Main {

  /* Problem 1: Structural Sub Type
   Complete the definition of CommonTy
   DO NOT USE "Any" in anywhere in your code.
   */
  object Problem1{
    class MyClass[A,B,C,D,E,F]() {
      type Func1 = { val a: A } => { val b: B }
      type Func2 = { val b: B } => { val a: A }

      type Ty1 = {
        def apply: { val func: Func1 ; val c: C } => { val b: B ; val d: D }
        val a: A
        val b: B
      }

      type Ty2 = {
        def apply: { val func: Func2 ; val e: E } => { val b: B ; val f: F }
        val a: A
        val c: C
      }

      /*
       Find "minimal" common supertype of Ty1 and Ty2,
       and replace "Any" with that type.
       */
      type CommonTy = {
        def apply: { val func: {} => { val a: A ; val b: B } ; val c: C ; val e: E } => { val b: B }
        val a: A
      }// <= fix it

    }
  }

  /* Problem 2
   Complete an implementation of 'IterDict'.
   IterDict represents a dictionary type.
   (See https://en.wikipedia.org/wiki/Associative_array for more information.)
   Briefly, you can store (key, value) pair into a dictionary,
   and search the stored value later from the associated key value.
   Here, your dictionary should be properly iterable.
   */
  object Problem2 {

    object IterDictImpl {
      //Write empty IterDict
      def empty[K, V](eqFunc: K => K => Boolean): IterDictImpl[K, V] = new IterDictImpl(eqFunc)(Nil)
    }

    class IterDictImpl[K, V](eqFunc: K => K => Boolean)(val data: List[(K, V)])
        extends IterDict[K, V] {

      def getValue = Option(data.head)

      def getNext = IterDictImpl(eqFunc)(data.tail)

      // When the given key already exists in this dictionary, overwrite the value.
      def add(k: K, v: V) : IterDict[K, V] = {
        if ()
      }

      // Return the associated value with the key. When there is no such key, return None.
      def find(k: K) : Option[V] = ???
    }

    // Write a function that iterates through given iterator and sum it.
    def sumElements[K](xs: Iter[(K, Int)]): Int = ???

  }

  /* Problem 3
   Define 'BiIterable' list and tree classes.
   These should provide bi-directional iteration, so you can freely move
   the iterator forward(getNext) or backward(getPrev) at any moment.
   For a BiIterableTree, the iterator should first visit the root, and then
   visit all nodes in the left subtree, and finally visit all nodes in the
   right subtree.
   (See preordering in https://en.wikipedia.org/wiki/Depth-first_search
   for more information.)
   At each end, your BiIter cannot go further, but should be able to go back
   to the reversed direction.
   For example:
   When you are on the first element,
   - getPrev.getValue gives None.
   - getPrev.getPrev.getValue also gives None.
   - getPrev.getPrev.getPrev.getPrev.getNext.getValue gives Some(first-element)
   Similarly, when you are on the last element,
   - getNext.getValue gives None.
   - getNext.getNext.getValue also gives None.
   - getNext.getNext.getNext.getNext.getPrev.getValue gives Some(last-element)
   You may create your own classes for this exercise.
   */
  object Problem3 {

    class BiIterableList[A](val data: List[A]) extends BiIterable[A] {
      def biIter: BiIter[A] = ???
    }

    sealed abstract class BiIterableTree[A] extends BiIterable[A] {
      // You can write something here
    }

    case class Empty[A]() extends BiIterableTree[A] {
      def biIter = ???
    }

    case class Node[A](value: A, left: BiIterableTree[A], right: BiIterableTree[A])
        extends BiIterableTree[A] {
      def biIter = ???
    }

  }

  /* Problem 4: Lazy list of prime numbers
   Define an infinite lazy list of all the prime numbers.
   This should return a lazy list which computes *all* inifitely many primes *lazily",
   which means that it computes the nth primes on demand.

   Finding the 10000th element(= 10000th prime) should complete in 5 secs.
   Hint: You may have to use tail recursion and "lazy val".

   NOTE: DO NOT pre-calculate the values of the prime numbers.
   Finding the 100th element should complete in 0.1 secs.
   */
  object Problem4 {
    val primes : LazyList[Int] = time {
      ???
    }
  }

}
