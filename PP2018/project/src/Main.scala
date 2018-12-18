package pp201802.proj
import scala.annotation.tailrec
import pp201802.proj.Data.DataBundle._
import scala.collection.immutable._

object Value {

  // Environment

  sealed abstract class Val
  case class VInt(n: Int) extends Val
  case class VTrue() extends Val
  case class VFalse() extends Val
  case class VNil() extends Val
  case class VPair(a: Val, b: Val) extends Val
  case class VDef(x: String, params: List[Arg], e: Expr) extends Val
  case class VRec(bs: List[Bind]) extends Val

  abstract class ConvertToScala[A] {
    def toInt(a:A) : Option[Int]
    def toBool(a:A) : Option[Boolean]
    def toPair(a:A) : Option[(A, A)]
    def isNil(a:A) : Boolean
    def isDef(a:A) : Boolean
    def isRec(a:A) : Boolean
  }

  implicit val valConv : ConvertToScala[Val] = new ConvertToScala[Val] {
    def toInt(a: Val) : Option[Int] =
      a match {
        case VInt(n) => Some(n)
        case _ => None
      }

    def toBool(a: Val) : Option[Boolean] =
      a match {
        case VTrue() => Some(true)
        case VFalse() => Some(false)
        case _ => None
      }

    def toPair(a: Val) : Option[(Val, Val)] =
      a match {
        case VPair(a, b) => Some((a, b))
        case _ => None
      }

    def isNil(a: Val): Boolean =
      a match {
        case VNil() => true
        case _ => false
      }

    def isDef(a: Val): Boolean =
      a match {
        case VDef(_, _, _) => true
        case _ => false
      }

    def isRec(a: Val): Boolean =
      a match {
        case VRec(_) => true
        case _ => false
      }
  }
}

object Main {
  import Value._

  class EvalException(val msg: String) extends Exception

  def myeval(e:Expr) : Val = {

    sealed abstract class Ref {
      val name: String
      def expr: Expr
    }

    case class RefVal(bind: BVal) extends Ref {
      val name: String = bind.x
      def expr: Expr = convValToExpr(myeval(bind.e))
    }

    case class RefLval(bind: BLval) extends Ref {
      val name: String = bind.x
      var expr: Expr = bind.e
    }

    case class RefDef(bind: BDef) extends Ref {
      val name: String = bind.f
      def expr: Expr = bind.e
    }

    def convValToExpr(value: Val): Expr =
      value match {
        case VInt(n) => EInt(n)
        case VTrue() => ETrue()
        case VFalse() => EFalse()
        case VNil() => ENil()
        case VPair(a, b) => ECons(convValToExpr(a), convValToExpr(b))
        case VDef(x, params, expr) => EName(x)
        case VRec(bs) => ERmk(bs)
      }

    def map[A, B](list: List[A])(f: A=>B): List[B] =
      list match {
        case head :: tail =>
          f(head) :: map(tail)(f)
        case Nil => Nil
      }

    def getRefs(bs: List[Bind]): List[Ref] = {
      def bindToRef(bind: Bind): Ref =
        bind match {
          case BVal(_, _) => RefVal(bind.asInstanceOf[BVal])
          case BLval(_, _) => RefLval(bind.asInstanceOf[BLval])
          case BDef(_, _, _) => RefDef(bind.asInstanceOf[BDef])
        }

      map(bs)(bindToRef)
    }

    def searchRefExpr(refs: List[Ref], name: String): Option[Expr] =
      refs match {
        case head :: _ if head.name == name => Some(head.expr)
        case _ :: tail => searchRefExpr(tail, name)
        case Nil => None
      }

    def searchBind(list: List[Bind], target: String): Option[Bind] =
      list match {
        case head :: tail =>
          head match {
            case BDef(x, _, _) if x == target => Some(head)
            case BVal(x, _) if x == target => Some(head)
            case BLval(x, _) if x == target => Some(head)
            case BDef(_, _, _) | BVal(_, _) | BLval(_, _) =>
              searchBind(tail, target)
          }
        case Nil => None
      }

    def substitute(refs: List[Ref], expr: Expr): Expr =
      expr match {
        case EName(x) =>
          searchRefExpr(refs, x) match {
            case Some(v) => v
            case None => throw new EvalException("There is no expression related to name " + x)
          }
        case EIf(econd, et, ef) =>
          EIf(substitute(refs, econd), substitute(refs, et), substitute(refs, ef))
        case ECons(eh, et) =>
          ECons(substitute(refs, eh), substitute(refs, et))
        case EFst(el) =>
          EFst(substitute(refs, el))
        case ESnd(el) =>
          ESnd(substitute(refs, el))
        case EApp(ef, eargs) =>
          EApp(substitute(refs, ef), substituteList(refs, eargs))
        case ELet(bs, eb) =>
          ELet(bs, substitute(refs, eb))
        case EMatch(e1, e2, hd, tl, e3) =>
          EMatch(substitute(refs, e1), substitute(refs, e2), hd, tl, e3)
        case ERfd(rec, fd) => // TODO: Let is overriding local Record field names?
          ERfd(substitute(refs, rec), fd)
          // TODO: implement substitute for the rest of the Expr types.
      }

    def substituteList(refs: List[Ref], exprs: List[Expr]): List[Expr] =
      exprs match {
        case head :: tail =>
          substitute(refs, head) :: substituteList(refs, tail)
        case Nil => Nil
      }

//    def substitute(e: Expr, params: List[Arg], eargs: List[Expr]) : Expr =
//      e match {
//        case EName(x) =>
//          getIdx(params, x, 0) match {
//            case Some(i) =>
//              get(eargs, i, 0) match {
//                case Some(expr) => expr
//                case None => throw new EvalException("function parameters could not be substituted. No such earg.")
//              }
//            case None => throw new EvalException("function parameters could not be substituted. No such parameter.")
//          }
//        case EIf(econd, et, ef) =>
//          EIf(substitute(econd, params, eargs), substitute(et, params, eargs), substitute(ef, params, eargs))
//        case ECons(eh, et) =>
//          ECons(substitute(eh, params, eargs), substitute(et, params, eargs))
//        case EFst(el) =>
//          EFst(substitute(el, params, eargs))
//        case ESnd(el) =>
//          ESnd(substitute(el, params, eargs))
//        case EApp(ef, subEargs) =>
//          EApp(substitute(ef, params, eargs), subEargs)
//        case ELet(bs: List[Bind], eb: Expr) =>
//          ELet(bs: List[Bind], substitute(eb, params, eargs))
//        case EMatch(e1, e2, hd, tl, e3) =>
//          EMatch(substitute(e1, params, eargs),
//            substitute(e2, params, eargs), hd, tl,
//            substitute(e3, params, eargs))
//        case ERfd(rec, fd) =>
//          ERfd(substitute(rec, params, eargs), fd)
//        case EPlus(e1, e2) =>
//          EPlus(substitute(e1, params, eargs), substitute(e2, params, eargs))
//        case EMinus(e1, e2) =>
//          EMinus(substitute(e1, params, eargs), substitute(e2, params, eargs))
//        case EMult(e1, e2) =>
//          EMult(substitute(e1, params, eargs), substitute(e2, params, eargs))
//        case EEq(e1, e2) =>
//          EEq(substitute(e1, params, eargs), substitute(e2, params, eargs))
//        case ELt(e1, e2) =>
//          ELt(substitute(e1, params, eargs), substitute(e2, params, eargs))
//        case EGt(e1, e2) =>
//          EGt(substitute(e1, params, eargs), substitute(e2, params, eargs))
//        case _ => e
//      }

    def getIdx(params: List[Arg], x: String, i: Int): Option[Int] =
      params match {
        case head :: tail =>
          head match {
            case AVname(`x`) => Some(i)
            case ANname(`x`) => Some(i)
            case _ => getIdx(tail, x, i + 1)
          }
        case Nil => None
      }

    def get(eargs: List[Expr], target: Int, curr: Int): Option[Expr] =
      if (eargs == Nil) None
      else if (target == curr) Some(eargs.head)
      else get(eargs.tail, target, curr + 1)

    def evaluateBind(bind: Bind): Val =
      bind match {
        case BVal(_, expr) => myeval(expr)
        case BLval(x, expr) => evaluateBind(BVal(x, expr))
        case BDef(f, params, expr) => VDef(f, params, e)
      }

    e match {
      case EInt(n) => VInt(n)
      case ETrue() => VTrue()
      case EFalse() => VFalse()
      case ENil() => VNil()
      case EName(s: String) => throw new EvalException("undefined name: " + s)
      case EIf(econd, et, ef) =>
        myeval(econd) match {
          case VTrue() => myeval(et)
          case VFalse() => myeval(ef)
          case _ => throw new EvalException("Boolean expression required for conditional execution.")
        }
      case ECons(eh, et) => VPair(myeval(eh), myeval(et))
      case EFst(el) =>
        myeval(el) match {
          case VPair(a, _) => a
          case _ => throw new EvalException("fst statement executed on non-pair object.")
        }
      case ESnd(el) =>
        myeval(el) match {
          case VPair(_, b) => b
          case _ => throw new EvalException("snd statement executed on non-pair object.")
        }
      case EApp(ef: Expr, eargs: List[Expr]) => // TODO: gotta fix this.
        myeval(ef) match {
          case VDef(_, params, expr) =>
            myeval(substitute(expr, params, eargs))
          case _ => throw new EvalException("evaluation called on non-function object.")
        }
      case ELet(bs: List[Bind], eb: Expr) =>
        var refs = getRefs(bs)

      //case EMatch(e1: Expr, e2: Expr, hd: String, tl: String, e3: Expr) =>
      case ERmk(bs) => VRec(bs)
      case ERfd(rec: Expr, fd: String) =>
        myeval(rec) match {
          case VRec(bs) =>
            searchBind(bs, fd) match {
              case Some(bind) =>
                bind match {
                  case BDef(f, params, expr) => VDef(f, params, expr)
                  case BVal(_, expr) => myeval(expr)
                  case BLval(_, expr) => myeval(expr)
                }
              case None => throw new EvalException("No such record named: " + fd)
            }
        }
      case EPlus(e1, e2) =>
        (myeval(e1), myeval(e2)) match {
          case (VInt(a), VInt(b)) => VInt(a + b)
          case (_, _) => throw new EvalException("+ operator used on non-integer values.")
        }
      case EMinus(e1, e2) =>
        (myeval(e1), myeval(e2)) match {
          case (VInt(a), VInt(b)) => VInt(a - b)
          case (_, _) => throw new EvalException("- operator used on non-integer values.")
        }
      case EMult(e1, e2) =>
        (myeval(e1), myeval(e2)) match {
          case (VInt(a), VInt(b)) => VInt(a * b)
          case (_, _) => throw new EvalException("* operator used on non-integer values.")
        }
      case EEq(e1, e2) =>
        (myeval(e1), myeval(e2)) match {
          case (VInt(a), VInt(b)) if a == b => VTrue()
          case (VInt(_), VInt(_)) => VFalse()
          case (_, _) => throw new EvalException("= operator used on non-integer values.")
        }
      case ELt(e1, e2) =>
        (myeval(e1), myeval(e2)) match {
          case (VInt(a), VInt(b)) if a < b => VTrue()
          case (VInt(_), VInt(_)) => VFalse()
          case (_, _) => throw new EvalException("< operator used on non-integer values.")
        }
      case EGt(e1, e2) =>
        (myeval(e1), myeval(e2)) match {
          case (VInt(a), VInt(b)) if a > b => VTrue()
          case (VInt(_), VInt(_)) => VFalse()
          case (_, _) => throw new EvalException("> operator used on non-integer values.")
        }
    }
  }

    //throw new EvalException("Not implemented yet")

}