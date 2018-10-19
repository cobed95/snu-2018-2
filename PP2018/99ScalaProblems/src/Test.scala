object Test extends App {
  //P01
  val list = List(1, 1, 2, 3, 5, 8)
  println(Solutions.last(list))

  //P02
  println(Solutions.penultimate(list))

  //P03
  println(Solutions.nth(2, list))

  //P04
  println(Solutions.length(list))

  //P05
  println(Solutions.reverse(list))

  //P06
  println(Solutions.isPalindrome(list))
  println(Solutions.isPalindrome(List(1, 2, 3, 2, 1)))

  //P07
  println(Solutions.flatten(List(List(1, 1), 2, List(3, List(5, 8)))))
  println(Solutions.flatten(List(List(3, List(5, 8)))))
  println(Solutions.flatten(List(List(), List(1, 1), 2, List(3, List(5, 8)))))

  //P08
  println(Solutions.compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  //P09
  println(Solutions.pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  //P10
  println(Solutions.encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  //P11
  println(Solutions.encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  //P12
  println(Solutions.decode(List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))))

  //P14
  println(Solutions.duplicate(List('a, 'b, 'c, 'c, 'd)))

  //P15
  println(Solutions.duplicateN(3, List('a, 'b, 'c, 'c, 'd)))

  //P16
  println(Solutions.drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))

  //P17
  println(Solutions.split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))

  //P18
  println(Solutions.slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))

  //P19
  println(Solutions.rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(Solutions.rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
}
