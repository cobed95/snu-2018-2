package pp201802.hw4.Data

sealed abstract class BST[K, V]
case class Leaf[K, V]() extends BST[K, V]
case class Node[K, V](key: K, value: V, left: BST[K, V], right: BST[K, V]) extends BST[K, V]

trait Letter {
  def setHeader(h: String): Letter
  def setBody(b: String): Letter
  def setFooter(f: String): Letter
}

abstract class Iter[I, A] {
  def getValue(i: I): Option[A]
  def getNext(i: I): I
}
   
abstract class IterF[F[_]] {
  def i[A]: Iter[F[A], A]
}

abstract class Functor[F[_]] {
  def map[A,B](f: A=>B)(x: F[A]) : F[B]
}

abstract class Ord[A] {
  def cmp(me: A, you: A): Int
  def ===(me: A, you: A): Boolean = cmp(me, you) == 0
  def <(me: A, you: A): Boolean = cmp(me, you) < 0
  def >(me: A, you: A): Boolean = cmp(me, you) > 0
  def <=(me: A, you: A): Boolean = cmp(me, you) <= 0
  def >=(me: A, you: A): Boolean = cmp(me, you) >= 0
}

abstract class Dict[D, K, V] {
  val empty: D
  def add(x: D, k: K, v: V): D
  def lookup(x: D, k: K): Option[V]
}
