package lectures.part2oop

object CaseClasses extends App {

  // les class params sont automatiquement fields
  case class Person(name: String, age: Int)

  val p = new Person("Yann", 34)
  println(p.name)

  // toString
  println(p)
  println(p.toString)

  // equals and hascode :o
  val p2 = new Person("Yann", 34)
  println(p.equals(p2)) // true nice :o
  println(p == p2) // ok ... uhm don't know if it's a good thing but ok
  println(p.hashCode())

  // copy method
  val p3 = p2.copy(age = 45)
  println(p3)
  println(p == p3)

  val thePerson = Person
  val p4 = Person.apply("Mary", 23)

  case object UnitedKingdom {
    def name: String = "UK"
  }


}
