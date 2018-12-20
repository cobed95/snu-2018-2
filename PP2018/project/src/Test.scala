package pp201802.projtest
import pp201802.proj.Main._
import pp201802.proj.Value._
import pp201802.proj.Data.DataBundle._
import pp201802.proj.Data.DataBundle.ExprToString._
import pp201802.proj.Lexer._
import pp201802.proj.Parser._
import pp201802.proj.MyParser._

object Test extends App {
  val myparser = false // change this into *true* if you want to use MyParser

  def print_result(b:Boolean) : Unit =
    if (b) println("O") else println("X")

//  def apply(code: String): List[Token] = {
//    parse(tokens, code) match {
//      case NoSuccess(msg, next) => throw new LexerException(msg)
//      case Success(result, next) => result
//    }
//  }

//  def shit(code: String): List[Token] = {
//    ProjLexer.parse(ProjLexer.tokens, code) match {
//      case ProjLexer.NoSuccess(msg, next) => throw new LexerException(msg)
//      case ProjLexer.Success(result, next) => result
//    }
//  }

  def run_eval(eval: Expr => Val)(code: String): Val = {
    val tokens = ProjLexer(code)
    val e: Expr = if (myparser) MyParser(tokens) else Parser(tokens)
    println(e)
    eval(e)
  }

  def run_myeval = run_eval(myeval) _

  def run_test(implicit conv:ConvertToScala[Val]) = {
    try {
      println("=================")
      println("1. Basic Test")

      { // 1
        val code = "(fst (cons 1 2))"
        val res = conv.toInt(run_myeval(code)) match {
          case Some(1) => true
          case _ => false
        }
        print_result(res)
      }

      { // 2
        val code = "(let ((val p (cons 1 (cons true nil)))) (cons 0 p))"
        val res = conv.toPair(run_myeval(code)) match {
          case Some(_) => true // this only checks whether the result is a pair.
          case _ => false
        }
        print_result(res)
      }

      { // 3
        val code = "(if true 10 20)"
        val res = conv.toInt(run_myeval(code)) match {
          case Some(10) => true
          case _ => false
        }
        print_result(res)
      }

      { // 4
        val code = "(let ((def f (x (by-name y)) (+ x y))) (app f 2 3))"
        val res = conv.toInt(run_myeval(code)) match {
          case Some(5) => true
          case _ => false
        }
        print_result(res)
      }

//      { // 5 TODO : failed here.
//        val code = "(let ((def g () (+ 1 2))) (let ((val f g)) f))"
//        val res = conv.isDef(run_myeval(code)) match {
//          case true => true
//          case false => false
//        }
//        print_result(res)
//      }

//      { // 6 TODO : failed here.
//        val code = "(let ((val a 10) (val b (+ a 1))) (* b 3))"
//        val res = conv.toInt(run_myeval(code)) match {
//          case Some(33) => true
//          case _ => false
//        }
//        print_result(res)
//      }

//      { // 7 TODO : failed here.
//        val code = "(let ((def f (x) (if (= x 0) 0 (+ x (app f (- x 1)))))) (let ((val g f)) (app g 5)))"
//        val res = conv.toInt(run_myeval(code)) match {
//          case Some(15) => true
//          case _ => false
//        }
//        print_result(res)
//      }

      { // 8
        val code = "(match-list (cons 1 2) (+ 4 5) (hdx tly) (+ hdx tly))"
        val res = conv.toInt(run_myeval(code)) match {
          case Some(3) => true
          case _ => false
        }
        print_result(res)
      }

//      { // 9 TODO : failed here.
//        val code = "(let ((def x () b) (lazy-val a (app x)) (val b 5)) a)"
//        val res = conv.toInt(run_myeval(code)) match {
//          case Some(5) => true
//          case _ => false
//        }
//        print_result(res)
//      }

      { // 10
        val code = "(rfd (rmk (lazy-val abc (+ 3 4))) abc)"
        val res = conv.toInt(run_myeval(code)) match {
          case Some(7) => true
          case _ => false
        }
        print_result(res)
      }

      // 11, 12 => hint for scope of "x"
//      { // 11 TODO failed here.
//        val code = "(let ((def x () (cons a b)) (val a 5) (val b 3)) (let ((val y x) (val a 4)) (app y)))" // (5, 3)
//        val res = conv.toPair(run_myeval(code)) match {
//          case Some((_, _)) => true // this only checks whether the result is a pair.
//          case _ => false
//        }
//        print_result(res)
//      }

//      { // 12 TODO : failed here.
//        val code = "(let ((def x () (cons a b)) (val a 5) (val b 3)) (let ((val a 4) (val y x)) (app y)))" // (5, 3)
//        val res = conv.toPair(run_myeval(code)) match {
//          case Some((_, _)) => true // this only checks whether the result is a pair.
//          case _ => false
//        }
//        print_result(res)
//      }
    } catch {
      case e : LexerException =>
        println("Lexer failed: " + e.msg)
      case e : ParserException =>
        println("Parser failed: " + e.msg)
      case e : EvalException =>
        println("myeval failed: " + e.msg)
    }
    try {
      println("=================")
      println("2. Infinite Lazy List Test")

      { // 1

        val code = "(let ((lazy-val inflist (let ((def x () (rmk (val hd 1) (lazy-val tl (app x))))) (app x)))) (rfd inflist hd))"
        val res = conv.toInt(run_myeval(code)) match {
          case Some(1) => true
          case _ => false
        }
        print_result(res)
      }

      { // 2 TODO: Failed here.
        val code = "(let ((lazy-val inflist (let ((def x () (rmk (val hd 1) (lazy-val tl (app x))))) (app x)))) (rfd inflist tl))"
        val res = conv.isRec(run_myeval(code)) match {
          case true => true
          case _ => false
        }
        print_result(res)
      }
    } catch {
      case e : LexerException =>
        println("Lexer failed: " + e.msg)
      case e : ParserException =>
        println("Parser failed: " + e.msg)
      case e : EvalException =>
        println("myeval failed: " + e.msg)
    }

    try {
      println("=================")
      println("3. Tailrec Test (should be finished)")
      val code = "(let ((def f (x n) (if (= x 0) n (app f (- x 1) (+ n x))))) (let ((val g f)) (app g 9999 0)))"
      val res = conv.toInt(run_myeval(code)) match {
        case Some(49995000) => true
        case _ => false
      }
      print_result(res)
    } catch {
      case e : LexerException =>
        println("Lexer failed: " + e.msg)
      case e : ParserException =>
        println("Parser failed: " + e.msg)
      case e : EvalException =>
        println("myeval failed: " + e.msg)
    }

    try {
      println("=================")
      println("4. Exception Handling Test")
      val code = "(try-catch (throw (if (> 3 4) 3 4)) (x) (+ x 1))"
      val res = conv.toInt(run_myeval(code)) match {
          case Some(5) => true
          case _ => false
        }
        print_result(res)
    } catch {
      case e : LexerException =>
        println("Lexer failed: " + e.msg)
      case e : ParserException =>
        println("Parser failed: " + e.msg)
      case e : EvalException =>
        println("myeval failed: " + e.msg)
    }

  }
  run_test
}
