package pp201802.proj
import scala.annotation.tailrec
import pp201802.proj.Data.DataBundle._
import scala.collection.immutable._

object Value {

  // Environment

  sealed abstract class Val
  case class Integer(n: Int) extends Val
  case class Bool(bool: Boolean) extends Val
  case class Pair(first: Val, second: Val) extends Val
  case class MyList[A]() extends Val
  case class MyCons[A](head: Option[A], tail: MyList[A]) extends MyList[A]
  case class MyNil[A]() extends MyList[A]
  case class Def(e: Expr) extends Val
  case class Record(e: Expr) extends Val
  
  abstract class ConvertToScala[A] {
    def toInt(a:A) : Option[Int]
    def toBool(a:A) : Option[Boolean]
    def toPair(a:A) : Option[(A, A)]
    def isNil(a:A) : Boolean
    def isDef(a:A) : Boolean
    def isRec(a:A) : Boolean
  }

  implicit val valConv : ConvertToScala[Val] = new ConvertToScala[Val] {
    def toInt(a: Val): Option[Int] =
      a match {
        case Integer(n) => Some(n)
        case _ => None
      }

    def toBool(a: Val): Option[Boolean] =
      a match {
        case Bool(bool) => Some(bool)
        case _ => None
      }

    def toPair(a: Val): Option[(Val, Val)] =
      a match {
        case Pair(first, second) => Some((first, second))
        case _ => None
      }

    def isNil(a: Val): Boolean =
      a match {
        case MyList() =>
          a match {
            case MyNil() => true
            case MyCons(_, _) => false
          }
        case _ => false
      }

    def isDef(a: Val): Boolean =
      a match {
        case Def(_) => true
        case _ => false
      }

    def isRec(a: Val): Boolean =
      a match {
        case Record(e) => true
        case _ => false
      }
  }
}

object Main {
  import Value._

  class EvalException(val msg: String) extends Exception

  def myeval(e:Expr) : Val = throw new EvalException("Not implemented yet")

}