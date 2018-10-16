package pp201802.hw2.Data

sealed abstract class IList
case class INil() extends IList
case class ICons(hd: Int, tl: IList) extends IList

sealed abstract class Exp
case class EInt(i: Int) extends Exp
case class EAdd(lhs: Exp, rhs: Exp) extends Exp
case class ESub(lhs: Exp, rhs: Exp) extends Exp
case class EMul(lhs: Exp, rhs: Exp) extends Exp
case class ESqr(e: Exp) extends Exp

object DataBundle {
  sealed abstract class MyList[A]
  case class MyNil[A]() extends MyList[A]
  case class MyCons[A](hd: A, tl: MyList[A]) extends MyList[A]

  sealed abstract class BTree[A]
  case class Leaf[A]() extends BTree[A]
  case class Node[A](value: A, left: BTree[A], right: BTree[A]) extends BTree[A]
}