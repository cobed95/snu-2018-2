package pp201802.hw4test
import pp201802.hw4.Data._
import pp201802.hw4.Main._
import reflect.runtime.universe._

object Test extends App {
  def print_result(b:Boolean) : Unit =
    if (b) println("PASS") else println("FAIL")

  // Problem 1
  {
    println("------Problem 1------");

    class MyLetter protected (header: String, body: String, footer: String)
      extends Problem1.BasicLetter(header, body, footer)
      with Problem1.Header_Dear with Problem1.Body_Newline with Problem1.Body_Doublespace with Problem1.Footer_Best
    {
      def this() = this("","","")
      override def mkLetter(h: String, b: String, f: String): Letter = new MyLetter(h, b, f)
    }

    val s0 = new MyLetter
    val s1 = s0.setHeader("Kim")
    val s2 = s1.setBody("Hello, This is final homework. Good   luck")
    val s3 = s2.setFooter("Lee")
    print_result(s3.toString == "Dear Kim\n\nHello, This is final homework.\n Good luck\n\nBest,\nLee")
  }

  // Problem 2
  {
    println("------Problem 2------");
    
    implicit def ListIterF: IterF[List] = new IterF[List] {
      def i[A]: Iter[List[A], A] = new Iter[List[A], A] {
        def getValue(i: List[A])= i.headOption
        def getNext(i: List[A])= i.tail
      }
    }

    implicit def ListFunctor: Functor[List] = new Functor[List] {
      def map[A,B](f: A=>B)(x: List[A]) = x.map(f)
    }

    var mr = (List(3,4,5))
    print_result(Problem2.mapReduce[List,Int,Int,Int]((a)=>a*a, mr, (b,c)=>b+c,0) == 50)
  }

  // Problem 3
  {
    println("------Problem 3------");

    implicit val intOrd : Ord[Int] = new Ord[Int] {
      def cmp(me: Int, you: Int) = me - you
    }

    val l = List(3, 5, 6, 2, 3, 2, 2)
    print_result(Problem3.countElements(l)(Problem3.listDict[Int, Int], Problem3.listIter[Int, Int]) == List((2, 3), (3, 2), (5, 1), (6, 1)))
    print_result(Problem3.countElements(l)(Problem3.BSTDict[Int, Int], Problem3.BSTIter[Int, Int]) == List((2, 3), (3, 2), (5, 1), (6, 1)))
  }
}

