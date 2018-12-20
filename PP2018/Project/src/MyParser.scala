package pp201802.proj.MyParser
import pp201802.proj.Data.DataBundle._
import pp201802.proj.Lexer._

class MyParserException(val msg: String) extends Exception

object MyParser {
  def parseToken[T: scala.reflect.ClassTag](index: Int)(implicit tokens: Vector[Token]): (T, Int) =
    if (index >= tokens.size) throw new MyParserException("Unexpected end of expression.")
    else {
      tokens(index) match {
        case t: T => (t, index + 1)
        case other => throw new MyParserException(s"Expected different token, found $other.")
      }
    }

  def parseHdTl(index: Int)(implicit tokens: Vector[Token]): ((TVNAME, TVNAME), Int) = {
    val (t1, index1) = parseToken[TVNAME](index)
    val (t2, index2) = parseToken[TVNAME](index1)
    ((t1, t2), index2)
  }

  def parseA(index: Int)(implicit tokens: Vector[Token]): (Arg, Int) =
    if (index >= tokens.size) throw new MyParserException("Unexpected end of expression.")
    else {
      tokens(index) match {
        case TVNAME(str) =>
          (AVname(str), index + 1)
        case _ =>
          val (_, index1) = parseToken[TLPAREN](index)
          val (tnname, index2) = parseToken[TNNAME](index1)
          val (_, index3) = parseToken[TRPAREN](index2)
          (ANname(tnname.str), index3)
      }
    }

  def parseB(index: Int)(implicit tokens: Vector[Token]): (Bind, Int) = {
    if (index >= tokens.size) throw new MyParserException("Unexpected end of expression.")
    else {
      val (_, index0) = parseToken[TLPAREN](index)
      tokens(index0) match {
        case TDEF() =>
          val (x, index1) = parseToken[TVNAME](index0 + 1)
          val (_, index2) = parseToken[TLPAREN](index1)
          val (params, index3) = parseStar(index2, (i, ts) => parseA(i)(ts))
          val (_, index4) = parseToken[TRPAREN](index3)
          val (e, index5) = parse(index4)
          val (_, index6) = parseToken[TRPAREN](index5)
          (BDef(x.str, params, e), index6)
        case TVAL() =>
          val (x, index1) = parseToken[TVNAME](index0 + 1)
          val (e, index2) = parse(index1)
          val (_, index3) = parseToken[TRPAREN](index2)
          (BVal(x.str, e), index3)
        case TLAZYVAL() =>
          val (x, index1) = parseToken[TVNAME](index0 + 1)
          val (e, index2) = parse(index1)
          val (_, index3) = parseToken[TRPAREN](index2)
          (BLval(x.str, e), index3)
      }
    }
  }

  def parseStar[T](index: Int, parse: (Int, Vector[Token]) => (T, Int), ts: List[T] = Nil)(implicit tokens: Vector[Token]): (List[T], Int) = {
    try {
      val (t, next) = parse(index, tokens)
      parseStar(next, parse, t :: ts)
    } catch {
      case e: Throwable => (ts.reverse, index)
    }
  }

  def parse(index: Int)(implicit tokens: Vector[Token]): (Expr, Int) = {
    tokens(index) match {
      case TVNAME(name) => (EName(name), index + 1)
      case TINT(number) => (EInt(number), index + 1)
      case TTRUE()      => (ETrue(), index + 1)
      case TFALSE()     => (EFalse(), index + 1)
      case TNIL()       => (ENil(), index + 1)
      case TLPAREN()    =>
        if (index + 1 >= tokens.size) throw new MyParserException("Unexpected end of expression.")
        else {
          val index0 = index + 2
          tokens(index + 1) match {
            case TIF() =>
              val (e1, index1) = parse(index0)
              val (e2, index2) = parse(index1)
              val (e3, index3) = parse(index2)
              val (_, index4) = parseToken[TRPAREN](index3)
              (EIf(e1, e2, e3), index4)

            case TCONS() =>
              val (e1, index1) = parse(index0)
              val (e2, index2) = parse(index1)
              val (_, index3) = parseToken[TRPAREN](index2)
              (ECons(e1, e2), index3)

            case TFST() =>
              val (e1, index1) = parse(index0)
              val (_, index2) = parseToken[TRPAREN](index1)
              (EFst(e1), index2)

            case TSND() =>
              val (e1, index1) = parse(index0)
              val (_, index2) = parseToken[TRPAREN](index1)
              (ESnd(e1), index2)

            case TMATCH() =>
              val (e1, index1) = parse(index0)
              val (e2, index2) = parse(index1)
              val (_, index3) = parseToken[TLPAREN](index2)
              val ((hd, tl), index4) = parseHdTl(index3)
              val (_, index5) = parseToken[TRPAREN](index4)
              val (e3, index6) = parse(index5)
              val (_, index7) = parseToken[TRPAREN](index6)
              (EMatch(e1, e2, hd.str, tl.str, e3), index7)

            case TLET() =>
              val (_, index1) = parseToken[TLPAREN](index0)
              val (bs, index2) = parseStar(index1, (i, ts) => parseB(i)(ts))
              val (_, index3) = parseToken[TRPAREN](index2)
              val (e, index4) = parse(index3)
              val (_, index5) = parseToken[TRPAREN](index4)
              (ELet(bs, e), index5)

            case TAPP() =>
              val (f, index1) = parse(index0)
              val (es, index2) = parseStar(index1, (i, ts) => parse(i)(ts))
              val (_, index3) = parseToken[TRPAREN](index2)
              (EApp(f, es), index3)

            case TRMK() =>
              val (binds, index1) = parseStar(index0, (i, ts) => parseB(i)(ts))
              val (_, index2) = parseToken[TRPAREN](index1)
              (ERmk(binds), index2)

            case TRFD() =>
              val (e, index1) = parse(index0)
              val (TVNAME(x), index2) = parseToken[TVNAME](index1)
              val (_, index3) = parseToken[TRPAREN](index2)
              (ERfd(e, x), index3)

            case TPLUS() =>
              val (e1, index1) = parse(index0)
              val (e2, index2) = parse(index1)
              val (_, index3) = parseToken[TRPAREN](index2)
              (EPlus(e1, e2), index3)

            case TMINUS() =>
              val (e1, index1) = parse(index0)
              val (e2, index2) = parse(index1)
              val (_, index3) = parseToken[TRPAREN](index2)
              (EMinus(e1, e2), index3)

            case TMULT() =>
              val (e1, index1) = parse(index0)
              val (e2, index2) = parse(index1)
              val (_, index3) = parseToken[TRPAREN](index2)
              (EMult(e1, e2), index3)

            case TEQ() =>
              val (e1, index1) = parse(index0)
              val (e2, index2) = parse(index1)
              val (_, index3) = parseToken[TRPAREN](index2)
              (EEq(e1, e2), index3)

            case TLT() =>
              val (e1, index1) = parse(index0)
              val (e2, index2) = parse(index1)
              val (_, index3) = parseToken[TRPAREN](index2)
              (ELt(e1, e2), index3)

            case TGT() =>
              val (e1, index1) = parse(index0)
              val (e2, index2) = parse(index1)
              val (_, index3) = parseToken[TRPAREN](index2)
              (EGt(e1, e2), index3)

            case TTHROW() =>
              val (e1, index1) = parse(index0)
              val (_, index2) = parseToken[TRPAREN](index1)
              (EThrow(e1), index2)

            case TTRYCATCH() =>
              val (e1, index1) = parse(index0)
              val (_, index2) = parseToken[TLPAREN](index1)
              val (x, index3) = parse(index2)
              val (_, index4) = parseToken[TRPAREN](index3)
              val (e2, index5) = parse(index4)
              val (_, index6) = parseToken[TRPAREN](index5)
              (ETrycatch(e1, x.toString, e2), index6)

            case _ =>
              throw new MyParserException("Not a valid token.")
          }
        }
      case _ =>
        throw new MyParserException("Not a valid token.")
    }
  }

  def apply(tokens: List[Token]): Expr =
    parse(0)(tokens.toVector)._1

}