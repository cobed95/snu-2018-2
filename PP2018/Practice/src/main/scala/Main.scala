object Main extends App {
  val gn = 0
  object foo {
    val a = 3
    def b = a + 1
    def f(x: Int) = b + x + gn
  }
  println(foo.f(0))

  type Foo = {val a: Int; def b: Int; def f(x: Int): Int}
  def g(x: Foo) = {
    val gn = 10
    x.f(0)
  }
  println(g(foo))
}
