package lectures.part1basics

object Strings extends App {

  val str: String = "Hello, I'm learning Scala"
  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)

  val numberStr = "42"
  val number = numberStr.toInt

  val frontBack = 'a' +: "text" :+ 'b'
  val backFront = "a" :+ "text" +: "b"
  println(frontBack)
  println(backFront)

  println(str.take(5))

  val name = "Yann";
  val age = 34
  val greeting = s"Hello, my name is $name and I will be turning ${age + 1} years old"
  println(greeting)

  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minute"
  println(myth)

  println(raw"This is a \n newline")
  val escaped = "This is a \n newline"
  println(s"$escaped")

}
