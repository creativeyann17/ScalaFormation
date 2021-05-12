package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract yeah ......
  abstract class Animal {
    val creatureType: String = "wild"

    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"

    /*override */ def eat = println("crunch crunch")
  }

  // traits (uhm ca sent l'interface)
  trait Carnivore {
    def eat(animal: Animal): Unit

    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    override def eat: Unit = println("nomnomnom")

    override def eat(animal: Animal): Unit = println(s"I'm a croc a,d I'm eating ${animal.creatureType}")
  }

  var dog = new Dog
  var croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract
  // 1 - traits - no constructor
  // 2 - extends 1 class but multiple traits
  // 3 - trait are behavior

  // les primitifs Scala sont AnyVal
  // les classes Scala sont AnyRef (comme Object), incluant String
  // Null herite de anyRef
  // Nothing herite + width AnyVal et AnyRef yolo
}
