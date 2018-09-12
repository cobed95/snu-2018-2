object Main extends App {
  println(MapReduce.sum(x=>x, 1, 1000000))
  println(MapReduce.product(x=>x * x, 1, 4))
}
