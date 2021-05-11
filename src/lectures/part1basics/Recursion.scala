package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else {
      println("Computing factorial of " + n + " and")
      val res = n * factorial((n - 1))
      println("Computed factorial of " + n + " and")
      res
    }
  }

  println(factorial(10)) // ok
  // println(factorial(5000)) // ko stackoverflow

  def anotherFactorial(n: Int): BigInt = {
    @tailrec // compiler va pas etre content si la fonction n'est pas tailrec hehe
    def factHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x < 1) accumulator
      else factHelper(x - 1, x * accumulator) // TAIL recursion, intelij l'indique dans la colonne
    }

    factHelper(n, 1)
  }

  println(anotherFactorial(5000))

  def concat(str: String, n: Int): String = {
    @tailrec
    def helper(count: Int, all: String): String = {
      if (count <= 1) all
      else helper(count - 1, all + str)
    }

    helper(n, "")
  }

  println(concat("yann", 100))

  @tailrec
  def concat2(str: String, n: Int, all: String = ""): String = {
    if (n <= 1) all
    else concat2(str, n - 1, str + all)
  }

  println(concat2("yann", 100))
}
