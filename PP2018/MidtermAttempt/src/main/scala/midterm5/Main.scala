package midterm5

import midterm5.Data._

object Main {
  sealed abstract class VarBinNode
  case class VarBinInternal(Op: Bop, left: VarBinNode, right: VarBinNode) extends VarBinNode
  case class VarBinLeaf(value: EInt) extends VarBinNode

  def parser(l: List[Token]): Exp = {
    def parse(stack: List[Exp], queue: List[Token]): Exp = {
      queue match {
        case Nil => stack.head
        case head :: tail =>
          head match {
            case TkBop(o) =>
              stack match {
                case Nil => EError()
                case _ :: Nil => EError()
                case first :: second :: bottom =>
                  parse(EOp(o, first, second) :: bottom, tail)
              }
            case TkInt(n) =>
              parse(EInt(n) :: stack, tail)
            case _ => EError()
          }
      }
    }

    def getPostFix(l: List[Token], stack: List[Token], queue: List[Token]): List[Token] = {
      println("stack: " + stack)
      println("queue: " + queue)
      l match {
        case Nil =>
          stack match {
            case Nil => queue
            case top :: bottom =>
              getPostFix(l, bottom, append(queue, top))
          }
        case head :: tail =>
          head match {
            case TkBop(o) =>
              stack match {
                case Nil => getPostFix(tail, head :: stack, queue)
                case top :: bottom =>
                  top match {
                    case TkLPar() => getPostFix(tail, head :: stack, queue)
                    case _ => getPostFix(l, bottom, append(queue, top))
                  }
              }
            case TkLPar() =>
              getPostFix(tail, head :: stack, queue)
            case TkRPar() =>
              stack match {
                case Nil => getPostFix(tail, stack, queue)
                case top :: bottom =>
                  top match {
                    case TkLPar() => getPostFix(tail, bottom, queue)
                    case _ => getPostFix(l, bottom, append(queue, top))
                  }
              }
            case _ => getPostFix(tail, stack, append(queue, head))
          }
      }
    }

    def append(l: List[Token], e: Token): List[Token] =
      l match {
        case Nil => List(e)
        case head :: tail =>
          head :: append(tail, e)
      }

    val postfix = getPostFix(l, Nil, Nil)
    println(postfix)
    parse(Nil, postfix)
  }

}
