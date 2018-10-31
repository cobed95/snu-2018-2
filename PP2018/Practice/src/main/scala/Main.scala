import AbstractDataTypes._

object Main extends App {
  val t1 = MyCons(3, MyCons(5, MyCons(7, MyNil())))
  println(sumElementsId(t1))

  println(sumElementsId(new IntCounter(100)))

  val t: MyTree[Int] = Node(3,
                          Node(4,
                            Node(2,
                              Empty(),
                              Empty()),
                            Node(3,
                              Empty(),
                              Empty())),
                          Node(5,
                            Empty(),
                            Empty()))

  println(sumElementsGen((x: Int)=>x)(t))
}
