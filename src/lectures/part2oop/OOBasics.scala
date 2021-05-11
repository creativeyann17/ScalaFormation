package lectures.part2oop

object OOBasics extends App {
  val person = new Person("Yann", 34)
  println(person.age)
  println(person.greet("Daniel"))
  println(person.greet)

  val c1 = new Counter(10)
  println(c1.count)
  println(c1.inc(2).dec().count)

  val a1 = new Writer("foo", "bar", 1950)
  val a2 = new Writer("foo", "bar", 1975)
  println(a1.fullname)

  val n1 = new Novel("book1", 2000, a1)
  println(n1.authorAge)
  println(n1.isWrittenBy(a1))
}

class Person(name: String, val age: Int = 0) {
  val x = 2; // important x est visible => person.x
  println(name + " " + age)

  def greet(name: String): String = s"${this.name} says Hi, $name"

  def greet: String = s"Hi, I'm $name"

  def this(name: String) = this(name, 0)

  def this() = this("Unknown")

}

class Novel(val name: String, val yearOfRelease: Int, val author: Writer) {
  def authorAge: Int = {
    yearOfRelease - author.yearOfBirth
  }

  def isWrittenBy(author: Writer): Boolean = {
    this.author == author // ok le but ici n'est pas de redefinir .equals, ca sera pour plus tard
  }

  def copy(newYearOfRelease: Int): Novel = {
    new Novel(name, newYearOfRelease, author)
  }
}

class Writer(val firstName: String, val surname: String, val yearOfBirth: Int) {
  val fullname = s"$firstName $surname"
}

class Counter(var count: Int) {

  def inc(value: Int = 1) = {
    new Counter((count + value))
  }

  def dec(value: Int = 1) = {
    new Counter((count - value))
  }
}
