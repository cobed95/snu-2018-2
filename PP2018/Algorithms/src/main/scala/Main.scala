object Main extends App {
  val list = List(6, 4, 1, 6, 8, 9, 3, 7, 2, 12, 7, 8)
  println(MergeSort.sort(list))

  val list2 = List(1, 2, 3, 4, 5)
  println(ListReverser.reverseList(list2))

  println(Numbers.power(2, 5))

  Numbers.pascalTriangle(10)
}
