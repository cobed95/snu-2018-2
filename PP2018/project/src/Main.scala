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

  sealed abstract class Binding {
    val name: String
  }

  case class ValBinding(name: String, value: Val) extends Binding

  case class

  case class DefBinding(bind: BDef) extends Binding {
    val name: String = bind.x
  }

  class EvalException(val msg: String) extends Exception

  def myeval(e:Expr) : Val = {
//    println(e.toString)

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
      def params: List[Arg] = bind.params
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

    def myevalIter(e: Expr, refs: List[Ref]): Val =
      e match {
        case EInt(n) => VInt(n)
        case ETrue() => VTrue()
        case EFalse() => VFalse()
        case ENil() => VNil()
        case EName(s) =>
//          println("name: " + s + " || refs: " + refs)
          searchRefExpr(refs, s) match {
            case Some(expr) =>
//              println("Got some expr!")
//              println("refs: " + refs)
              myevalIter(expr, refs)
            case None => throw new EvalException("Undefined name: " + s)
          }
        case EIf(econd, et, ef) =>
          myevalIter(econd, refs) match {
            case VTrue() => myevalIter(et, refs)
            case VFalse() => myevalIter(ef, refs)
            case _ => throw new EvalException("Non-boolean expression is used for conditional statement.")
          }
        case ECons(eh, et) => VPair(myevalIter(eh, refs), myevalIter(et, refs))
        case EFst(el) =>
          myevalIter(el, refs) match {
            case VPair(a, _) => a
            case _ => throw new EvalException("Fst is called on non-pair object.")
          }
        case ESnd(el) =>
          myevalIter(el, refs) match {
            case VPair(_, b) => b
            case _ => throw new EvalException("Snd is called on non-pair object.")
          }
        case EApp(ef, eargs) =>
          call(ef, eargs, refs)
        case ELet(bs, eb) =>
//          println("Current Refs: " + refs)
//          println("Bind List: " + bs)
//          println("Expression: " + eb)
//          println("New Refs: " + updateRefs(refs, bs))
          myevalIter(eb, updateRefs(refs, bs))
        case EMatch(e1, e2, hd, tl, e3) =>
          myevalIter(e1, refs) match {
            case VPair(a, b) =>
              val newRefs = updateRefs(refs, List(BVal(hd, convValToExpr(a)), BVal(tl, convValToExpr(b))))
              myevalIter(e3, newRefs)
            case VNil() => myevalIter(e2, refs)
            case _ => throw new EvalException("match-list called on non-pair object.")
          }
        case ERmk(bs) => VRec(bs)
        case ERfd(rec, fd) =>
          myevalIter(rec, refs) match {
            case VRec(bs) =>
              val newRefs = getRefs(bs)
              searchRefExpr(newRefs, fd) match {
                case Some(expr) => myevalIter(expr, refs)
                case None => throw new EvalException("field " + fd + " does not exist in rec.")
              }
            case _ => throw new EvalException(rec.toString + " is not a record type.")
          }
        case EPlus(e1, e2) =>
          (myevalIter(e1, refs), myevalIter(e2, refs)) match {
            case (VInt(a), VInt(b)) => VInt(a + b)
            case _ => throw new EvalException("+ called on non-integer values.")
          }
        case EMinus(e1, e2) =>
          (myevalIter(e1, refs), myevalIter(e2, refs)) match {
            case (VInt(a), VInt(b)) => VInt(a - b)
            case _ => throw new EvalException("- called on non-integer values.")
          }
        case EMult(e1, e2) =>
          (myevalIter(e1, refs), myevalIter(e2, refs)) match {
            case (VInt(a), VInt(b)) => VInt(a * b)
            case _ => throw new EvalException("* called on non-integer values.")
          }
        case EEq(e1, e2) =>
          (myevalIter(e1, refs), myevalIter(e2, refs)) match {
            case (VInt(a), VInt(b)) if a == b => VTrue()
            case (VInt(_), VInt(_)) => VFalse()
            case _ => throw new EvalException("= called on non-integer values.")
          }
        case ELt(e1, e2) =>
          (myevalIter(e1, refs), myevalIter(e2, refs)) match {
            case (VInt(a), VInt(b)) if a < b => VTrue()
            case (VInt(_), VInt(_)) => VFalse()
            case _ => throw new EvalException("< called on non-integer values.")
          }
        case EGt(e1, e2) =>
          (myevalIter(e1, refs), myevalIter(e2, refs)) match {
            case (VInt(a), VInt(b)) if a > b => VTrue()
            case (VInt(_), VInt(_)) => VFalse()
            case _ => throw new EvalException("> called on non-integer values.")
          }
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
        case head :: _ if head.name == name =>
          head match {
            case RefLval(_) =>
              val refLval = head.asInstanceOf[RefLval]
              refLval.expr = convValToExpr(myevalIter(head.expr, refs))
              Some(head.expr)
            case RefDef(_) =>
              val refDef = head.asInstanceOf[RefDef]
              refDef.params match {
                case Nil => Some(refDef.expr)
                case _ => throw new EvalException("Not enough arguments for " + refDef.name)
              }
            case RefVal(_) => Some(head.expr)
          }
        case _ :: tail => searchRefExpr(tail, name)
        case Nil => None
      }

    def searchRef(refs: List[Ref], name: String): Option[Ref] =
      refs match {
        case head :: _ if head.name == name => Some(head)
        case _ :: tail => searchRef(tail, name)
        case Nil => None
      }

    def updateRefs(refs: List[Ref], bs: List[Bind]): List[Ref] = {
      def updateRefsIter(oldRefs: List[Ref], newRefs: List[Ref]): List[Ref] = {
//        println("oldRefs: " + oldRefs)
//        println("newRefs: " + newRefs)
        newRefs match {
          case head :: tail =>
            updateRefsIter(updateOneRef(oldRefs, head), tail)
          case Nil => oldRefs
        }
      }

      def updateOneRef(refs: List[Ref], ref: Ref): List[Ref] =
        refs match {
          case head :: tail if head.name == ref.name =>
//            println("head.name: " + head.name)
//            println("ref.name: " + ref.name)
            ref :: tail
          case head :: tail =>
//            println("head: " + head + " || ref: " + ref)
            head :: updateOneRef(tail, ref)
          case Nil => ref :: Nil
        }

      val newRefs = getRefs(bs)

      updateRefsIter(refs, newRefs)
    }

    def argsToBinds(args: List[Arg], eargs: List[Expr]): List[Bind] = {
      def argToBind(arg: Arg, earg: Expr): Bind =
        arg match {
          case AVname(x) => BVal(x, earg)
          case ANname(x) => BLval(x, earg)
        }

      (args, eargs) match {
        case (arg :: aTail, earg :: eTail) =>
          argToBind(arg, earg) :: argsToBinds(aTail, eTail)
        case (Nil, Nil) =>
          Nil
        case _ => throw new EvalException("not enough arguments.")
      }
    }

    def call(ef: Expr, eargs: List[Expr], refs: List[Ref]): Val =
      ef match {
        case EName(s) =>
          searchRef(refs, s) match {
            case Some(ref) =>
              ref match {
                case RefDef(_) =>
                  val refDef = ref.asInstanceOf[RefDef]
                  val binds = argsToBinds(refDef.params, eargs)
                  myevalIter(refDef.expr, updateRefs(refs, binds))
                case RefVal(_) =>
                  ref.expr match {
                    case EName(_) =>
                      call(ref.expr, eargs, refs)
                    case _ => throw new EvalException("app called on non-function.")
                  }
                case RefLval(_) =>
                  val expr = convValToExpr(myevalIter(ref.expr, refs))
                  expr match {
                    case EName(_) =>
                      updateRefs(refs, List(BVal(ref.name, ref.expr)))
                      call(expr, eargs, refs)
                    case _ => throw new EvalException("app called on non-function.")
                  }
              }
            case None => throw new EvalException("name " + s + " is undefined.")
          }
        case _ => throw new EvalException(ef.toString + " is not a function.")
      }
    myevalIter(e, Nil)
  }
}