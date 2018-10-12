object Main extends App {
  val f = (x: Int) => x
  val g = new {
    def apply(x: Int): Int = x
  }
  println(f.apply(10))
  println(g.apply(10))
}
