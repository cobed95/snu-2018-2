package pp201802.hw3.Data
import scala.annotation.tailrec

object DataBundle {

  var timeTaken : Long = 0
  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    timeTaken = (t1 - t0)/1000000
    println("Elapsed time: " + timeTaken + "ms")
    result
  }

  abstract class Iter[A] {
    def getValue: Option[A]
    def getNext: Iter[A]
  }

  // Problem 2
  abstract class IterDict[K, V] extends Iter[(K, V)] {
    def add(k: K, v: V) : IterDict[K, V]
    def find(k: K) : Option[V]
  }

  // Problem 3
  abstract class BiIter[A] extends Iter[A] {
    def getNext: BiIter[A]
    def getPrev: BiIter[A]
  }
  abstract class BiIterable[A] {
    def biIter: BiIter[A]
  }

  // Problem 4
  abstract class LazyList[A] {
    def head : Option[A]
    def tail : LazyList[A]
    def nth(n: Int) : Option[A] = LazyList.nth(this, n)
  }
  object LazyList {
    @tailrec def nth[A](l: LazyList[A], n: Int) : Option[A] =
      l.head match {
        case None => None
        case Some(hd) => if (n <= 0) Some(hd) else nth(l.tail, n-1)
      }
  }
  case class LNil[A]() extends LazyList[A] {
    val head = None
    val tail = this
  }
  class LCons[A](hdtl: =>(A, LazyList[A])) extends LazyList[A] {
    lazy val (hd,tl) = hdtl
    def head = Some(hd)
    def tail = tl
  }
  object LCons{def apply[A](hdtl : =>(A,LazyList[A]))=new LCons(hdtl)}

}
