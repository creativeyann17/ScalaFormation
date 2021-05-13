package lectures.part3fp

object AnonymousFunctions extends App {
  // val doubler = (x: Int) => x * 2 // fonctionne aussi
  val doubler: (Int => Int) = (x) => x * 2 // equivalent a new Functions1 .... LAMBDA yeah
  println(doubler(10))

  val adder: ((Int, Int) => Int) = (a, b) => {
    a + b
  }
  println(adder(1, 2))

  val justDoSomething: () => Int = () => 3
  println(justDoSomething) // ref de la fonction mais ca ne l'appel pas
  println(justDoSomething())

  // autre facon d'ecrire
  val stringToInt = { (str: String) => str.toInt }

  val niceIncrementer: Int => Int = _ + 1 // equivalent to (x)=>x+1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a+b


}
