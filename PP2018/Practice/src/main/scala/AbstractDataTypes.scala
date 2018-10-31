object AbstractDataTypes {
  abstract class Iter[A] {
    def getValue: Option[A]
    def getNext: Iter[A]
  }

  abstract class Iterable[A] {
    def iter: Iter[A]
  }

  def sumElements[A](f: A=>Int)(xs: Iter[A]): Int =
    xs.getValue match {
      case None => 0
      case Some(n) => f(n) + sumElements(f)(xs.getNext)
    }

  def sumElementsId[A](xs: Iter[Int]): Int =
    sumElements((x: Int)=>x)(xs)

  sealed abstract class MyList[A] extends Iter[A] {
    def append(lst: MyList[A]): MyList[A]
  }
  case class MyNil[A]() extends MyList[A] {
    def getValue = None
    def getNext = this
    def append(lst: MyList[A]) = lst
  }
  case class MyCons[A](hd: A, tl: MyList[A]) extends MyList[A] {
    def getValue = Some(hd)
    def getNext = tl
    def append(lst: MyList[A]) = MyCons(hd, tl.append(lst))
  }

  class IntCounter(n: Int) extends Iter[Int] {
    def getValue = if (n >= 0) Some(n) else None
    def getNext = new IntCounter(n - 1)
  }

  def sumElementsGen[A](f: A=> Int)(xs: Iterable[A]): Int =
    sumElements(f)(xs.iter)

  sealed abstract class MyTree[A] extends Iterable[A] {
    def iter: MyList[A]
  }
  case class Empty[A]() extends MyTree[A] {
    val iter = MyNil()
  }
  case class Node[A](value: A, left: MyTree[A], right: MyTree[A]) extends MyTree[A] {
    val iter = MyCons(value, left.iter.append(right.iter))
  }
}
