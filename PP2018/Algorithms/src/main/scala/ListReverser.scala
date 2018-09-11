object ListReverser {
  def reverseList[T](list: List[T]): List[T] = {
    def reverseListIter[T](list: List[T], result: List[T]): List[T] = {
      (list, result) match {
        case (Nil, r) =>
          result
        case (a :: as, b) =>
          reverseListIter(as, a :: result)
      }
    }

    reverseListIter(list, List())
  }
}
