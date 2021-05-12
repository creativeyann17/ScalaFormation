package lectures.part2oop

object Objects {

  object Person { // object = SINGLETON , cool spring-boot serait ravit! comme struct en C
    val N_EYES = 2

    def canFly: Boolean = false

    // factory
    def from(mother: Person, father: Person) = new Person("Bobbie")

    def apply(mother: Person, father: Person) = new Person("Bobbie")
  }

  class Person(val name: String) {

  }
  // ecrire la partie static et instance (class) s'appelle COMPANIONS
  // en gros tout ce qui aurait dû/pû etre static c'est 'object' car singleton

  def main(args: Array[String]): Unit = { // same as extends App, qui contient main method
    println(Person.N_EYES)
    println(Person.canFly)

    val mary = Person
    val john = Person
    println(mary == john) // true same ref of singleton Person

    val mary2 = new Person("mary") // interessant de voir que Scala arrive a faire la difference entre les deux entitées
    val john2 = new Person("john")
    println(mary2 == john2) // false not same instance

    val bobbie1 = Person.from(mary2, john2);
    val bobbie2 = Person.apply(mary2, john2);
    val bobbie3 = Person(mary2, john2); // because of 'magic' apply
  }

}
