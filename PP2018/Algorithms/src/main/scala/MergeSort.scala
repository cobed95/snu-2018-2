object MergeSort extends App {
  def sort[T](list: List[T])(implicit ordering: Ordering[T]): List[T] = {
    def merge(left: List[T], right: List[T]): List[T] = {
      (left, right) match {
        case (Nil, Nil) => Nil
        case (l, Nil) => l
        case (Nil, r) => r

        case (a :: as, b :: bs) if ordering.lt(a, b) =>
          a :: merge(as, right)
        case (a :: as, b :: bs) =>
          b :: merge(left, bs)
      }
    }

    val length = list.length
    if (length <= 1) list
    else {
      val (left, right) = list.splitAt(length / 2)
      merge(sort(left), sort(right))
    }
  }
}
