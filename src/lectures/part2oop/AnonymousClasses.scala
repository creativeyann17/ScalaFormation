package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }


  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("ahahahahhaha")
  }

  /*
  equivalent to
    class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("ahahahahhaha")
  }
  val funnyAnimal: Animal = new AnonymousClasses$$anon$1
   */

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi = println(s"Hi I'm $name")
  }

  val jim = new Person("") { // even if Person isn't abstract
    override def sayHi = println(s"Hi I'm jim")
  }

  funnyAnimal.eat
  jim.sayHi

}
