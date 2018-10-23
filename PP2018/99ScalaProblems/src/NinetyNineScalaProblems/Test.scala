package NinetyNineScalaProblems

import NinetyNineScalaProblems.Solutions._

object Test extends App {
  //P01
  val list = List(1, 1, 2, 3, 5, 8)
  println(last(list))

  //P02
  println(penultimate(list))

  //P03
  println(nth(2, list))

  //P04
  println(length(list))

  //P05
  println(reverse(list))

  //P06
  println(isPalindrome(list))
  println(isPalindrome(List(1, 2, 3, 2, 1)))

  //P07
  println(flatten(List(List(1, 1), 2, List(3, List(5, 8)))))
  println(flatten(List(List(3, List(5, 8)))))
  println(flatten(List(List(), List(1, 1), 2, List(3, List(5, 8)))))

  //P08
  println(compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  //P09
  println(pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  //P10
  println(encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  //P11
  println(encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  //P12
  println(decode(List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))))

  //P14
  println(duplicate(List('a, 'b, 'c, 'c, 'd)))

  //P15
  println(duplicateN(3, List('a, 'b, 'c, 'c, 'd)))

  //P16
  println(drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))

  //P17
  println(split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))

  //P18
  println(slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))

  //P19
  println(rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))

  //P20
  println(removeAt(1, List('a, 'b, 'c, 'd)))

  //P21
  println(insertAt('new, 1, List('a, 'b, 'c, 'd)))

  //P22
  println(range(4, 9))

  //P23
  println(randomSelect(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h)))

  //P24
  println(lotto(6, 49))

  //P25
  println(randomPermute(List('a, 'b, 'c, 'd, 'e, 'f)))

  //P56
  println(Node('a', Node('b'), Node('c')).isSymmetric)
  println(Node('a', Node('b'), Node('c', Node('d'), End)).isSymmetric)

  //P57
  println(Tree.fromList(List(3,2,5,7,1)))
  println(Tree.fromList(List(5,3,18,1,4,12,21)).isSymmetric)
  println(Tree.fromList(List(3,2,5,7,4)).isSymmetric)

}
