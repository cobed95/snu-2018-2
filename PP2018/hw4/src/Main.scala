package pp201802.hw4
import pp201802.hw4.Data._
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import scala.language.higherKinds
import scala.annotation.tailrec

object Main {

  /* Problem 1: Letter stacking
   Implement a letter decorator using stacking.
   A letter consists of three parts (header, body, and footer) and each part has two decorators.
   You can use scala String operator and libraries in advance.
   */
   
  object Problem1 {
    class BasicLetter protected (header: String, body: String, footer: String) extends Letter {
      override val toString = header + "\n\n" + body + "\n\n" + footer
      def this() = this("", "", "")
      def mkLetter(h: String, b: String, f: String): Letter =
        new BasicLetter(h, b, f)
      def setHeader(h: String): Letter = mkLetter(h, body, footer)
      def setBody(b: String): Letter = mkLetter(header, b, footer)
      def setFooter(f: String): Letter = mkLetter(header, body, f)
    }

    // Add "Dear " before a header
    trait Header_Dear extends Letter {
      abstract override def setHeader(h: String): Letter =
        super.setHeader("Dear " + h)
    }
    // Add "Hello " before a header
    trait Header_Hello extends Letter {
      abstract override def setHeader(h: String): Letter =
        super.setHeader("Hello " + h)
    }
    // Add newlines after each period of a body
    trait Body_Newline extends Letter {
      abstract override def setBody(b: String): Letter = {
        val list = b.toList
        val inserted = insertNewLines(list)
        val result = mapListToString(inserted)
        super.setBody(result)
      }
    }
    // Replace successive whitespaces (spaces, tabs, and newlines) with a single space
    trait Body_Doublespace extends Letter {
      abstract override def setBody(b: String): Letter = {
        val list = b.toList
        val removed = removeRedundantSpace(list)
        val result = mapListToString(removed)
        super.setBody(result)
      }
    }
    // Add "Best,\n" before a footer
    trait Footer_Best extends Letter {
      abstract override def setFooter(f: String): Letter =
        super.setFooter("Best,\n" + f)
    }
    // Add today's date in form of "YYYY-MM-DD\n" at the front of a footer
    trait Footer_Day extends Letter {
      abstract override def setFooter(f: String): Letter = {
        val date = java.time.LocalDate.now
        super.setFooter(date + "\n" + f)
      }
    }

    def insertNewLines(list: List[Char]): List[Char] =
      list match {
        case head :: tail if head != '.' =>
          head :: insertNewLines(tail)
        case head :: tail =>
          head :: '\n' :: insertNewLines(tail)
        case Nil => Nil
      }

    def removeRedundantSpace(list: List[Char]): List[Char] =
      list match {
        case head :: tail if head != ' ' && head != '\t' && head != '\n' =>
          head :: removeRedundantSpace(tail)
        case _ :: tail =>
          tail match {
            case tailHead :: _ if tailHead != ' ' && tailHead != '\t' && tailHead != '\n' =>
              ' ' :: removeRedundantSpace(tail)
            case _ :: tailTail =>
              removeRedundantSpace(' ' :: tailTail)
            case Nil => ' ' :: tail
          }
        case Nil => Nil
      }

    def mapListToString(list: List[Char]): String =
      list match {
        case head :: tail =>
          head + mapListToString(tail)
        case Nil => ""
      }
  }

  /* Problem 2: Mapreduce
   Implement mapReduce using Iter and Functor.
   You must use the map defined in Functor rather than directly applying f as in the lecture notes.
   */

  object Problem2 {

    def mapReduce[F[_], A, B ,C](f: A => B, a: F[A], reduce: (B, C) => C, init: C)
                                (implicit functor: Functor[F], iter: IterF[F]): C =
      iter.i.getValue(a) match {
        case Some(_) =>
          reduce(iter.i.getValue(functor.map(f)(a)).get, mapReduce(f, iter.i.getNext(a), reduce, init))
        case None => init
      }

  }


  /* Problem 3
   Implement countElements, which counts occurrences of each element in a given list, using dictionary.
   Furthermore, implement two versions of Dict and Iter using List and BST
   (binary search tree, See https://en.wikipedia.org/wiki/Binary_search_tree).
   Note that Iter must iterate through a dictionary in ascending order of keys.
   */

  object Problem3 {

    def reverse[A, B](list: List[(A, B)]): List[(A, B)] = {
      var listAux: List[(A, B)] = list
      var reversed: List[(A, B)] = Nil
      while (listAux != Nil) {
        reversed = listAux.head :: reversed
        listAux = listAux.tail
      }
      reversed
    }

    def countElements[D, A](l: List[A])(implicit dict: Dict[D, A, Int], iter: Iter[D, A]): List[(A, Int)] = {

      def countElementsIter(l: List[A], pairs: D): D = {
        l match {
          case head :: tail =>
            dict.lookup(pairs, head) match {
              case Some(v) => countElementsIter(tail, dict.add(pairs, head, v + 1))
              case None => countElementsIter(tail, dict.add(pairs, head, 1))
            }
          case Nil => pairs
        }
      }

      def map(pairs: D): List[(A, Int)] = {
        var list: List[(A, Int)] = Nil
        var pairsAux = pairs
        while (iter.getValue(pairsAux).isDefined) {
          val key = iter.getValue(pairsAux).get
          list = (key, dict.lookup(pairsAux, key).get) :: list
          pairsAux = iter.getNext(pairsAux)
        }
        reverse(list)
      }

      val pairs = countElementsIter(l, dict.empty)
      map(pairs)
    }

    class MergeList[K, V](first: (K, V)) {
      def merge(tail: List[(K, V)]): List[(K, V)] = first :: tail
    }

    object MergeList {
      def apply[K, V](first: (K, V)): MergeList[K, V] = new MergeList[K, V](first)
    }

    def reconstructList[K, V](list: List[(K, V)], merges: List[MergeList[K, V]]): List[(K, V)] =
      merges match {
        case head :: tail => reconstructList(head.merge(list), tail)
        case Nil => list
      }

    implicit def listDict[K, V](implicit ord: Ord[K]): Dict[List[(K, V)], K, V] = new Dict[List[(K, V)], K, V] {

      val empty: List[(K, V)] = Nil

      def add(x: List[(K, V)], k: K, v: V): List[(K, V)] = {
        addIter(x, k, v, Nil)
//        var left: List[(K, V)] = Nil
//        var right = x
//        while (right != Nil && ord.>(k, right.head._1)) {
//          left = right.head :: left
//          right = right.tail
//        }
//        if (right != Nil && ord.===(k, right.head._1))
//          right = right.tail
//        left = (k, v) :: left
//        while (right != Nil) {
//          left = right.head :: left
//          right = right.tail
//        }
//        reverse(left)
      }

      @tailrec
      def addIter(x: List[(K, V)], k: K, v: V, merges: List[MergeList[K, V]]): List[(K, V)] =
        x match {
          case (head @ (key, _)) :: tail if ord.>(k, key) =>
            addIter(tail, k, v, MergeList(head) :: merges)
          case (`k`, _) :: tail =>
            reconstructList((k, v) :: tail, merges)
          case (key, _) :: _ if ord.<(k, key) =>
            reconstructList((k, v) :: x, merges)
          case Nil =>
            reconstructList((k, v) :: Nil, merges)
        }

      @tailrec
      def lookup(x: List[(K, V)], k: K): Option[V] =
        x match {
          case head :: _ if ord.<(k, head._1) =>
            None
          case head :: _ if ord.===(k, head._1) =>
            Some(head._2)
          case Nil => None
          case _ :: tail =>
            lookup(tail, k)
        }
    }

    implicit def listIter[K, V]: Iter[List[(K, V)], K] = new Iter[List[(K, V)], K] {

      def getValue(i: List[(K, V)]): Option[K] =
        i match {
          case head :: _ => Some(head._1)
          case Nil => None
        }

      def getNext(i: List[(K, V)]): List[(K, V)] = i.tail
    }

    sealed abstract class Merge[K, V] {
      def key: K
      def value: V
      def merge(bst: BST[K, V]): BST[K, V]
    }

    case class MergeLeft[K, V](key: K, value: V, leftChild: BST[K, V]) extends Merge[K, V] {
      def merge(rightChild: BST[K, V]): BST[K, V] =
        Node(key, value, leftChild, rightChild)
    }

    case class MergeRight[K, V](key: K, value: V, rightChild: BST[K, V]) extends Merge[K, V] {
      def merge(leftChild: BST[K, V]): BST[K, V] =
        Node(key, value, leftChild, rightChild)
    }

    @tailrec
    def reconstruct[K, V](node: BST[K, V], merges: List[Merge[K, V]]): BST[K, V] =
      merges match {
        case head :: tail =>
          reconstruct(head.merge(node), tail)
        case Nil => node
      }

    implicit def BSTDict[K, V](implicit ord: Ord[K]): Dict[BST[K, V], K, V] = new Dict[BST[K, V], K, V] {

      val empty: BST[K, V] = Leaf()

      def add(x: BST[K, V], k: K, v: V): BST[K, V] =
        addIter(x, k, v, Nil)

      @tailrec
      def addIter(x: BST[K, V], k: K, v: V, merges: List[Merge[K, V]]): BST[K, V] = {
        x match {
          case Node(`k`, _, left, right) =>
            reconstruct(Node(k, v, left, right), merges)
          case Node(key, value, left, right) if ord.<(k, key) =>
            addIter(left, k, v, MergeRight(key, value, right) :: merges)
          case Node(key, value, left, right) if ord.>(k, key) =>
            addIter(right, k, v, MergeLeft(key, value, left) :: merges)
          case Leaf() =>
            reconstruct(Node(k, v, Leaf(), Leaf()), merges)
        }
      }

      @tailrec
      def lookup(x: BST[K, V], k: K): Option[V] =
        x match {
          case Node(key, _, _, right) if ord.>(k, key) =>
            lookup(right, k)
          case Node(key, _, left, _) if ord.<(k, key) =>
            lookup(left, k)
          case Node(key, value, _, _) if ord.===(k, key) =>
            Some(value)
          case Leaf() => None
        }
    }

    implicit def BSTIter[K, V]: Iter[BST[K, V], K] = new Iter[BST[K, V], K] {
      def getValue(i: BST[K, V]): Option[K] =
        i match {
          case Node(keyI, _, left, _) =>
            left match {
              case Node(_, _, _, _) => getValue(left)
              case Leaf() => Some(keyI)
            }
          case Leaf() => None
        }

      def getNext(i: BST[K, V]): BST[K, V] =
        deleteMin(i)

      def deleteMin(i: BST[K, V]): BST[K, V] =
        deleteMinIter(i, Nil)

      @tailrec
      def deleteMinIter(i: BST[K, V], merges: List[Merge[K, V]]): BST[K, V] =
        i match {
          case Node(key, value, left @ Node(_, _, _, _), right) =>
            deleteMinIter(left, MergeRight(key, value, right) :: merges)
          case Node(_, _, Leaf(), right) =>
            reconstruct(right, merges)
          case Leaf() =>
            Leaf()
        }
    }
  }
}
