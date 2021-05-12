package lectures.part2oop

object Inheritance extends App {

  // single class inheritance
  // final / sealed (interdit lheritage a l'exterieur de ce fichier)
  class Animal {
    val creatureType = "wild"

    //final
    def eat = println("animal miam miam")
    // private / protected existent
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("cat crunch crunch")
    }

  }

  class Dog(override val creatureType: String) extends Animal {
    // override val creatureType: String = "domestic" // cool

    override def eat = {
      super.eat
      println("dog miam miam")
    }

  }

  val cat = new Cat
  cat.crunch
  println(cat.creatureType)

  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  val dog = new Dog("domestic")
  dog.eat
  println(dog.creatureType)

  // type substitution
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overriding != overloading (meme methode avec different params
  // final = meme comportement que en JAVA
  // class - peut pas etre extends
  // method - peut pas etre override

}
