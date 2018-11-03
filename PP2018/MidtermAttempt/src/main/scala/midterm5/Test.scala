package midterm5

import midterm5.Main._
import midterm5.Data._

object Test extends App {
  def print_result(b:Boolean) : Unit =
    if (b) println("O") else println("X")

  println("parser(List(TkInt(4), TkBop(OpSub()), TkInt(8), TkBop(OpMul()), TkInt(9))) is equal to " + parser(List(TkInt(4), TkBop(OpSub()), TkInt(8), TkBop(OpMul()), TkInt(9))))
  println("parser(List(TkInt(8), TkBop(OpAdd()), TkInt(9), TkBop(OpMul()), TkLPar(), TkInt(8), TkBop(OpAdd()), TkInt(4), TkRPar(), TkBop(OpMul()), TkInt(3), TkBop(OpSub()), TkInt(9), TkBop(OpMul()), TkInt(7))) is equal to " + parser(List(TkInt(8), TkBop(OpAdd()), TkInt(9), TkBop(OpMul()), TkLPar(), TkInt(8), TkBop(OpAdd()), TkInt(4), TkRPar(), TkBop(OpMul()), TkInt(3), TkBop(OpSub()), TkInt(9), TkBop(OpMul()), TkInt(7))))
  // 8 + 9 * (8 + 4) * 3 - 9 * 7
  // 7 * (9 - (3 * (4 + 8) * (9 + 8)))
  println("parser(List(TkLPar(), TkInt(5), TkBop(OpMul()), TkInt(9))) is equal to " + parser(List(TkLPar(), TkInt(5), TkBop(OpMul()), TkInt(9))))
}
