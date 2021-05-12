package lectures.part2oop

object Generics extends App {

  class MyList[+T] {
    // use type A inside the class def
    def add[B >: T](element: B): MyList[B] = ???
    /*
    A =  Cat
    B = Animal
     */

  }

  class MyMap[Key, Value]

  val listOfInt = new MyList[Int]
  val listOfString = new MyList[String]

  val emptyListOfInt = MyList.empty[Int]

  // generic methods
  object MyList {
    def empty[T]: MyList[T] = new MyList[T]
  }

  // variance probleme
  class Animal

  class Cat extends Animal

  class Dog extends Animal

  // 1. YES List[Cat] extends List[Animal} = COVARIANCE
  class CovariantList[+T] // franchement le '+' tombe du ciel ... mais ok

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ?? => return list of animal

  // 2. NO = INVARIANCE
  class InvariantList[T]

  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal] // peut pas mettre Cat

  // 3 Hell, no! CONTRAVARIANCE
  class Trainer[-T]

  val trainer: Trainer[Cat] = new Trainer[Animal] // heureux de savoir que c'est possible mais ... non xD

  // bounded types
  class Cage[A <: Animal](animal: A) // T extends Animal.class

  val cage = new Cage(new Dog)

  class Car

  // val newCage = new Cage(new Car) // erreur uniquement au runtime


}
