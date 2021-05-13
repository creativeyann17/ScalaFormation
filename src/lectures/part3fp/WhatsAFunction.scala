package lectures.part3fp

object WhatsAFunction extends App {
  // JVM is OOP oriented, how Scala works ?

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  trait MyFunction[A, B] {
    def apply(element: A): B = ???
  }

  println(doubler(2))

  // function types = Function1[A,B]
  val stringToInt = new Function1[String, Int] { // jusqu'à 22
    override def apply(v1: String): Int = v1.toInt
  }

  println(stringToInt("42") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  // ALL SCALA functions are objects

  val concat: ((String, String) => String) = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  println(concat("a", "b"))

  /*
    val superAdder: (Int => Int => Int) = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Int => Int = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }
   */

  // version lambda
  val superAdder = (x: Int) => (y: Int) => x + y

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function

}

