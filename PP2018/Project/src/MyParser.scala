package pp201802.proj.MyParser
import pp201802.proj.Data.DataBundle._
import pp201802.proj.Lexer._

class MyParserException(val msg: String) extends Exception

object MyParser {
  def apply(tokens:List[Token]): Expr =
    throw new MyParserException("Not implemented yet.")
}
