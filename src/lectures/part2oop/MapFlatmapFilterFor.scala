package lectures.part2oop

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  println(list.map(_ + 1))
  println(list.map(String.valueOf(_)))

  println(list.filter(_ % 2 == 0))
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair)) // toPair(_)

  var numbers = List(1, 2, 3, 4)
  var chars = List('a', 'b', 'c', 'd')
  var colors = List("black", "white")

  // "iterations"
  println(numbers.flatMap(x => chars.flatMap(y => colors.map(z => s"$x$y$z"))))

  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    x <- numbers if (x % 2 == 0)
    y <- chars
    z <- colors
  } yield (s"$x$y$z")

  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // possible mais pas trop utilise
  list.map { x =>
    x * 2
  }


}
