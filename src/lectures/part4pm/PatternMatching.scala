package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
  val rand = new Random

  val x = rand.nextInt(10)

  val description = x match {
    case 1 => "the one"
    case 2 => "double of nothing"
    case 3 => "third time is the charm"
    case _ => "something else"
  }

  println(x)
  println(description)

  case class Person(name: String, age: Int)


  val bob = new Person("bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi my name is $n and I'm $a years old and I can drink in the US"
    case Person(n, a) => s"Hi my name is $n and I'm $a years old"
    case _ => "I don't know who I'm"
  }

  println(greeting)

  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("1")

  animal match {
    case Dog(someBreed) => println(someBreed)
  }

}
