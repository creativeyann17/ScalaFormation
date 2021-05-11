package lectures.part2oop

object MethodsNotations extends App {

  // Person exist deja dans OOBasics
  class Person(val name: String, val favoriteMovie: String, var age: Int = 0) {
    def likes(movie: String): Boolean = favoriteMovie == movie

    def +(person: Person): String = s"${name} is hanging out with ${person.name}"

    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)

    def unary_! : String = name.reverse // :o

    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    def isAlive: Boolean = true

    def learns(stuff: String): String = s"$name learns $stuff"

    def learnsScala: String = learns("Scala")

    def apply(): String = s"Hello my name is $name and like $favoriteMovie"

    def apply(n: Int): String = s"$name watchs $favoriteMovie $n times"
  }

  val p1 = new Person("Yann", "Contact")
  println(p1.likes("Contact"))
  println(p1 likes "Contact") // infix notation / operator notation (syntactic sugar??) / foncitonne que si method avec 1 param

  // operators
  val p2 = new Person("Toto", "Friends")
  println(p1.+(p2))
  println(p1 + p2) // ok en gros comme en c++ redef d'operateur

  // all operators are methods, donc tout les type sont des classes, y'a pas de 'primitif' comme java
  println(1 + 2)
  println(1.+(2))

  // prefix notations
  val x = -1
  val y = 1.unary_-
  println(!p1) // ok j'avoue c'est plutot cool
  println(p1.unary_!)

  // postfix notations
  println(p1 isAlive)

  // apply
  println(p1.apply()) // uhm c'est un peu comme avoir une mthode compute() tjrs a portée de mains
  println(p1()) // uhm c'est un peu comme avoir une mthode compute() tjrs a portée de mains

  println((p1 + "Geek à tps partiel") ())
  println((+p1).age)
  println(p1 learns "Something")
  println(p1 learnsScala)
  println(p1(2))

}



