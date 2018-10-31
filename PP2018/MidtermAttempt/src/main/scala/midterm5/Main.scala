package midterm5

import midterm5.Data._

object Main {
  sealed abstract class VarBinNode
  case class VarBinInternal(Op: Bop, left: VarBinNode, right: VarBinNode) extends VarBinNode
  case class VarBinLeaf(value: EInt) extends VarBinNode

  def parser(l: List[Token]): Exp = {
    def getEOp(first: Exp, second: TkBop, third: Exp): Exp =
      EOp(second.o, first, third)

    def slice(l: List[Any], sliced: List[Any]): List[Any] = {
      l match {
        case (TkLPar() :: tail) =>
          slice(tail, sliced

      }
    }
  }

}
