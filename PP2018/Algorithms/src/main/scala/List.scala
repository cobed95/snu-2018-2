abstract sealed class List[+T] {
  def head: T
  def tail: List[T]
  def isEmpty: Boolean

  def append[A >: T](x: A): List[A] = {
    if (isEmpty) List.make(x)
    else List.make(head, tail.append(x))
  }

  def prepend[A >: T](x: A): List[A] = List.make(x, this)

  def concat[A >: T](xs: List[A]): List[A] = {
    if (isEmpty) xs
    else tail.concat(xs).prepend(head)
  }

  def remove[A >: T](x: A): List[A] = {
    if (isEmpty) fail("Can't find " + x + " in this list.")
    else if (x != head) List.make(head, tail.remove(x))
    else tail
  }

  def apply(n: Int): T = {
    if (isEmpty) fail("Index out of bounds.")
    else if (n < 0) fail("Index (< 0) out of bounds.")
    else if (n == 0) head
    else tail(n - 1)
  }

  def contains[A >: T](x: A): Boolean = {
    if (isEmpty) false
    else if (head != x) tail.contains(x)
    else true
  }

  def fail(m: String) = throw new NoSuchElementException(m)
}

case object Nil extends List[Nothing] {
  def head: Nothing = fail("An empty list.")
  def tail: List[Nothing] = fail("An empty list.")

  def isEmpty: Boolean = true
}

case class Cons[T](head: T, tail: List[T]) extends List[T] {
  def isEmpty: Boolean = false
}

object List {
  def empty[T]: List[T] = Nil
  def make[T](x: T, t: List[T] = Nil): List[T] = Cons(x, t)
  def apply[T](xs: T*): List[T] = {
    var r: List[T] = List.empty
    for (x <- xs.reverse) r = r.prepend(x)
    r
  }
}