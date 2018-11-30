package pp201802.hw4
import pp201802.hw4.Data._
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

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
      def mkLetter(h: String, b: String, f: String): Letter = ???
      def setHeader(h: String): Letter = ???
      def setBody(b: String): Letter = ???
      def setFooter(f: String): Letter = ???
    }

    // Add "Dear " before a header
    trait Header_Dear extends Letter {
      ???
    }
    // Add "Hello " before a header
    trait Header_Hello extends Letter {
      ???
    }
    // Add newlines after each period of a body
    trait Body_Newline extends Letter {
      ???
    }
    // Replace successive whitespaces (spaces, tabs, and newlines) with a single space
    trait Body_Doublespace extends Letter {
      ???
    }
    // Add "Best,\n" before a footer
    trait Footer_Best extends Letter {
      ???
    }
    // Add today's date in form of "YYYY-MM-DD\n" at the front of a footer
    trait Footer_Day extends Letter {
      ???
    }
  }

  /* Problem 2: Mapreduce
   Implement mapReduce using Iter and Functor.
   You must use the map defined in Functor rather than directly applying f as in the lecture notes.
   */

  object Problem2 {
    def mapReduce[F[_], A, B ,C](f: A => B, a: F[A], reduce: (B, C) => C, init: C)
                                (implicit functor: Functor[F], iter: IterF[F]): C = ???
  }


  /* Problem 3
   Implement countElements, which counts occurrences of each element in a given list, using dictionary.
   Furthermore, implement two versions of Dict and Iter using List and BST
   (binary search tree, See https://en.wikipedia.org/wiki/Binary_search_tree).
   Note that Iter must iterate through a dictionary in ascending order of keys.
   */

  object Problem3 {
    def countElements[D, A](l: List[A])(implicit dict: Dict[D, A, Int], iter: Iter[D, A]): List[(A, Int)] = ???

    implicit def listDict[K, V](implicit ord: Ord[K]): Dict[List[(K, V)], K, V] = ???

    implicit def listIter[K, V]: Iter[List[(K, V)], K] = ???

    implicit def BSTDict[K, V](implicit ord: Ord[K]): Dict[BST[K, V], K, V] = ???

    implicit def BSTIter[K, V]: Iter[BST[K, V], K] = ???
  }
}
