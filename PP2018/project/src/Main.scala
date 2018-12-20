package pp201802.proj

import scala.annotation.tailrec
import scala.collection.immutable._
import pp201802.proj.Data.DataBundle._

object Value {
  class EvalException(val msg: String) extends Exception
  class InterpreterException(val value: Val) extends Exception

  sealed abstract class Box

  case class BoxVal(value: Val) extends Box
  class BoxLazyVal(val env: () => Env, val expr: Expr) extends Box {
    var value: Val = _
  }

  object BoxLazyVal {
    def apply(env: () => Env, expr: Expr): BoxLazyVal = new BoxLazyVal(env, expr)
  }

  case class Env(dict: Map[String, Box]) {
    def bind(name: String, box: Box): Env =
      Env(dict + (name -> box))

    def lookup(name: String): Option[Box] =
      dict get name
  }

  object Env {
    def empty: Env = Env(Map.empty)
  }

  // Environment

  sealed abstract class Val
  case class VInt(n: Int) extends Val
  case class VTrue() extends Val
  case class VFalse() extends Val
  case class VNil() extends Val
  case class VPair(a: Val, b: Val) extends Val
  case class VDef(env: () => Env, params: List[Arg], e: Expr) extends Val
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

  def myeval(e:Expr) : Val =
    try {
      eval(e, Env.empty)
    } catch {
      case e: InterpreterException =>
        e.value
    }

  def eval(e: Expr, env: Env): Val =
    e match {
      case EInt(n) => VInt(n)
      case ETrue() => VTrue()
      case EFalse() => VFalse()
      case ENil() => VNil()
      case EName(s) =>
        env.lookup(s) match {
          case Some(boxLazyVal: BoxLazyVal) =>
            if (boxLazyVal.value ne null)
              boxLazyVal.value
            else {
              val evaluated = eval(boxLazyVal.expr, boxLazyVal.env())
              boxLazyVal.value = evaluated
              evaluated
            }
          case Some(BoxVal(value)) =>
            value
          case None =>
            throw new EvalException("Undefined name: " + s)
        }
      case EIf(econd, et, ef) =>
        eval(econd, env) match {
          case VTrue() =>
            eval(et, env)
          case VFalse() =>
            eval(ef, env)
          case _ =>
            throw new EvalException("Non-boolean condition: " + econd)
        }
      case EApp(ef, eargs) =>
        eval(ef, env) match {
          case VDef(envAux, params, expr) =>
            if (params.size != eargs.size)
              throw new EvalException("")
            else {
              var newEnv = envAux()
              for ((arg, earg) <- params zip eargs) {
                arg match {
                  case AVname(x) =>
                    newEnv = newEnv.bind(x, BoxVal(eval(earg, env)))
                  case ANname(x) =>
                    newEnv = newEnv.bind(x, BoxLazyVal(() => env, earg))
                }
              }

              eval(expr, newEnv)
            }
          case _ => throw new EvalException("Non-function object: " + ef)
        }
      case ELet(bs, eb) =>
        lazy val newEnv: Env =
          bs.foldLeft(env) { (e, binding) =>
            binding match {
              case BVal(name, expr) =>
                e.bind(name, BoxVal(eval(expr, e)))
              case BLval(name, expr) =>
                e.bind(name, BoxLazyVal(() => newEnv, expr))
              case BDef(name, params, expr) =>
                e.bind(name, BoxVal(VDef(() => newEnv, params, expr)))
            }
          }

        eval(eb, newEnv)
      case ECons(eh, et) =>
        VPair(eval(eh, env), eval(et, env))
      case EFst(el) =>
        eval(el, env) match {
          case VPair(a, _) => a
          case _ =>
            throw new EvalException("Non-record object: " + el)
        }
      case ESnd(el) =>
        eval(el, env) match {
          case VPair(_, b) => b
          case _ =>
            throw new EvalException("Non-record object: " + el)
        }
      case EMatch(e1, e2, hd, tl, e3) =>
        eval(e1, env) match {
          case VNil() => eval(e2, env)
          case VPair(a, b) =>
            var newEnv = env
            newEnv = newEnv.bind(hd, BoxVal(a))
            newEnv = newEnv.bind(tl, BoxVal(b))
            eval(e3, newEnv)
          case _ =>
            throw new EvalException("Non-pair object: " + e1)
        }
      case ERmk(bs) => VRec(bs)
      case ERfd(rec, fd) =>
        eval(rec, env) match {
          case VRec(bs) =>
            lazy val newEnv: Env =
            bs.foldLeft(env) { (e, binding) =>
              binding match {
                case BVal(name, expr) =>
                  e.bind(name, BoxVal(eval(expr, e)))
                case BLval(name, expr) =>
                  e.bind(name, BoxLazyVal(() => newEnv, expr))
                case BDef(name, params, expr) =>
                  e.bind(name, BoxVal(VDef(() => newEnv, params, expr)))
              }
            }
            newEnv.lookup(fd) match {
              case Some(boxLazyVal: BoxLazyVal) =>
                if (boxLazyVal.value ne null)
                boxLazyVal.value
                else {
                  val evaluated = eval(boxLazyVal.expr, boxLazyVal.env())
                  boxLazyVal.value = evaluated
                  evaluated
                }
              case Some(BoxVal(value)) =>
                value
              case None =>
                throw new EvalException("Undefined field: " + fd)
            }
          case _ =>
            throw new EvalException("Non-record object: " + rec)
        }
      case EPlus(e1, e2) =>
        (eval(e1, env), eval(e2, env)) match {
          case (VInt(v1), VInt(v2)) => VInt(v1 + v2)
          case _ =>
            throw new EvalException("Non-int objects on +.")
        }
      case EMinus(e1, e2) =>
        (eval(e1, env), eval(e2, env)) match {
          case (VInt(v1), VInt(v2)) => VInt(v1 - v2)
          case _ =>
            throw new EvalException("Non-int objects on -.")
        }
      case EMult(e1, e2) =>
        (eval(e1, env), eval(e2, env)) match {
          case (VInt(v1), VInt(v2)) => VInt(v1 * v2)
          case _ =>
            throw new EvalException("Non-int objects on *.")
        }
      case EEq(e1, e2) =>
        (eval(e1, env), eval(e2, env)) match {
          case (VInt(v1), VInt(v2)) =>
            if (v1 == v2) VTrue()
            else VFalse()
          case _ =>
            throw new EvalException("Non-int objects on =.")
        }
      case ELt(e1, e2) =>
        (eval(e1, env), eval(e2, env)) match {
          case (VInt(v1), VInt(v2)) =>
            if (v1 < v2) VTrue()
            else VFalse()
          case _ =>
            throw new EvalException("Non-int objects on <.")
        }
      case EGt(e1, e2) =>
        (eval(e1, env), eval(e2, env)) match {
          case (VInt(v1), VInt(v2)) =>
            if (v1 > v2) VTrue()
            else VFalse()
          case _ =>
            throw new EvalException("Non-int objects on >.")
        }
      case EThrow(expr) =>
        throw new InterpreterException(eval(expr, env))
      case ETrycatch(e1, x, e2) =>
        try {
          eval(e1, env)
        } catch {
          case ex: InterpreterException =>
            var newEnv = env
            newEnv = newEnv.bind(x, BoxVal(ex.value))
            eval(e2, newEnv)
        }
    }
}