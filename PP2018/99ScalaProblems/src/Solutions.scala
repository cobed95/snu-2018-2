object Solutions {
  //P01 Find the last element of a list.
  def last[T](list: List[T]): Option[T] =
    list match {
      case Nil => None
      case head :: Nil => Some(head)
      case head :: tail => last(tail)
    }

  //P02 Find the last but one element of a list.
  def penultimate[T](list: List[T]): Option[T] =
    list match {
      case Nil => None
      case head :: Nil => None
      case head :: last :: Nil => Some(head)
      case head :: tail => penultimate(tail)
    }

  //P03 Find the Kth element of a list.
  def nth[T](idx: Int, list: List[T]): Option[T] = {
    def nthIter[T](idx: Int, list: List[T], current: Int): Option[T] =
      list match {
        case Nil => None
        case head :: tail if idx == current => Some(head)
        case head :: tail => nthIter(idx, tail, current + 1)
      }

    nthIter(idx, list, 0)
  }

  //P04 Find the number of elements of a list.
  def length[T](list: List[T]): Int = {
    def lengthIter[T](list: List[T], length: Int): Int =
      list match {
        case Nil => length
        case _ :: tail => lengthIter(tail, length + 1)
      }

    lengthIter(list, 0)
  }

  //P05 Reverse a list.
  def reverse[T](list: List[T]): List[T] = {
    def reverseIter[T](left: List[T], right: List[T]): List[T] =
      (left, right) match {
        case (Nil, right) => right
        case (head :: tail, right) => reverseIter(tail, head :: right)
      }

    reverseIter(list, Nil)
  }

  //P06 Find out whether a list is a palindrome.
  def isPalindrome[T](list: List[T]): Boolean = {
    def isPalindromeIter[T](list: List[T], reversed: List[T]): Boolean =
      (list, reversed) match {
        case (Nil, Nil) => true
        case (listHead :: listTail, reverseHead :: reverseTail) if listHead != reverseHead =>
          false
        case (listHead :: listTail, reverseHead :: reverseTail) =>
          isPalindromeIter(listTail, reverseTail)
      }

    val reversed = reverse(list)
    isPalindromeIter(list, reversed)
  }

  //P07 Flatten a nested list structure.
  def flatten(list: List[Any]): List[Any] = {
    list match {
      case Nil => Nil
      case head :: tail if head.isInstanceOf[List[Any]] =>
        val headList = head.asInstanceOf[List[Any]]
        flatten(headList) ::: flatten(tail)
      case head :: tail =>
        head :: flatten(tail)
    }
  }

  //P08 Eliminate consecutive duplicates of list elements.
  def compress[T](list: List[T]): List[T] = {
    def compressIter[T](list: List[T], duplicate: T): List[T] =
      list match {
        case Nil => Nil
        case head :: tail if head == duplicate =>
          compressIter(tail, duplicate)
        case head :: tail =>
          head :: compressIter(tail, head)
      }

    list match {
      case Nil => Nil
      case head :: tail => head :: compressIter(tail, head)
    }
  }

  //P09 Pack consecutive duplicates of list elements into sublists.
  def pack[T](list: List[T]): List[List[T]] = {
    def packIter(list: List[T], packed: List[T]): List[List[T]] =
      (list, packed) match {
        case (Nil, packed) => List(packed)
        case (listHead :: listTail, packedHead :: _) if listHead == packedHead =>
          packIter(listTail, listHead :: packed)
        case (listHead :: listTail, packedHead :: _) =>
          packed :: packIter(listTail, List(listHead))
      }

    list match {
      case Nil => Nil
      case head :: tail => packIter(tail, List(head))
    }
  }

  //P10 Run-length encoding of a list.
  def encode[T](list: List[T]): List[(Int, T)] = {
    def encodeIter[T](list: List[T], encoded: (Int, T)): List[(Int, T)] =
      (list, encoded) match {
        case (Nil, encoded) => List(encoded)
        case (head :: tail, (n, value)) if head == value =>
          encodeIter(tail, (n + 1, value))
        case (head :: tail, _) =>
          encoded :: encodeIter(tail, (1, head))
      }

    list match {
      case Nil => Nil
      case head :: tail => encodeIter(tail, (1, head))
    }
  }

  //P11 Modified run-length encoding.
  def encodeModified[T](list: List[T]): List[Any] = {
    def encodeModifiedIter[T](list: List[T], encoded: (Int, T)): List[Any] =
      (list, encoded) match {
        case (Nil, (1, value)) => List(value)
        case (Nil, encoded) => List(encoded)
        case (head :: tail, (n, value)) if head == value =>
          encodeModifiedIter(tail, ((n + 1), value))
        case (head :: tail, (n, value)) =>
          n match {
            case 1 => value :: encodeModifiedIter(tail, (1, head))
            case _ => (n, value) :: encodeModifiedIter(tail, (1, head))
          }
      }

    list match {
      case Nil => Nil
      case head :: tail => encodeModifiedIter(tail, (1, head))
    }
  }

  //P12 Decode a run-length encoded list.
  def decode[T](encoded: List[(Int, T)]): List[T] =
    encoded match {
      case Nil => Nil
      case (n, value) :: tail =>
        n match {
          case 1 => value :: decode(tail)
          case _ => value :: decode((n - 1, value) :: tail)
        }
    }

  //P14 Duplicate the elements of a list.
  def duplicate[T](list: List[T]): List[T] =
    list match {
      case Nil => Nil
      case head :: tail => head :: head :: duplicate(tail)
    }

  //P15 Duplicate the elements of a list a given number of times.
  def duplicateN[T](repeat: Int, list: List[T]): List[T] = {
    def duplicateNIter[T](repeat: Int, list: List[T], count: Int): List[T] =
      (list, count) match {
        case (Nil, _) => Nil
        case (head :: tail, n) if n == repeat =>
          duplicateNIter(repeat, tail, 0)
        case (head :: _, n) =>
          head :: duplicateNIter(repeat, list, n + 1)
      }

    duplicateNIter[T](repeat, list, 0)
  }

  //P16 Drop every Nth element from a list.
  def drop[T](n: Int, list: List[T]): List[T] = {
    def dropIter[T](drop: Int, list: List[T], count: Int): List[T] =
      (list, count) match {
        case (Nil, _) => Nil
        case (head :: tail, n) if n == drop =>
          dropIter(drop, tail, 1)
        case (head :: tail, n) =>
          head :: dropIter(drop, tail, n + 1)
      }

    dropIter(n, list, 1)
  }

  //P17 Split a list into two parts.
  def split[T](regex: Int, list: List[T]): (List[T], List[T]) = {
    def splitIter[T](regex: Int, list: List[T], count: Int): (List[T], List[T]) =
      list match {
        case Nil => (Nil, Nil)
        case head :: tail if count < regex =>
          val split = splitIter(regex, tail, count + 1)
          (head :: split._1, split._2)
        case head :: tail =>
          val split = splitIter(regex, tail, count + 1)
          (split._1, head :: split._2)
      }

    splitIter(regex, list, 0)
  }

  //P18 Extract a slice from a list.
  def slice[T](from: Int, to: Int, list: List[T]): List[T] = {
    def sliceIter[T](from: Int, to: Int, list: List[T], current: Int): List[T] =
      list match {
        case Nil => Nil
        case head :: tail if current < from =>
          sliceIter(from, to, tail, current + 1)
        case head :: tail if current >= to =>
          sliceIter(from, to, tail, current + 1)
        case head :: tail =>
          head :: sliceIter(from, to, tail, current + 1)
      }

    sliceIter(from, to, list, 0)
  }

  //P19 Rotate a list N places to the left
  def rotate[T](offset: Int, list: List[T]): List[T] = {
    def rotateIter[T](offset: Int, list: List[T], current: Int): List[T] =
      if (current == offset) list
      else
        list match {
          case Nil => Nil
          case head :: tail => rotateIter(offset, append(head, tail), current + 1)
        }

    def append[T](element: T, list: List[T]): List[T] =
      list match {
        case Nil => List(element)
        case head :: tail => head :: append(element, tail)
      }

    if (offset >= 0)
      rotateIter(offset, list, 0)
    else
      rotateIter(offset, list, -length(list))
  }

  //P20 Remove the Kth element from a list.
  /*
  def removeAt[T](k: Int, list: List[T]): (List[T], T) = {
    def removeAtIter[T](k: Int, list: List[T], current: Int): (List[T], T) =
      list match {

      }
  }
  */

}
