package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int) = { // intelij est capable de trouver le type de retour
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParamaterlessFunction(): Int = 42

  println(aParamaterlessFunction())
  println(aParamaterlessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = { // intelij ne peut pas trouver le type de retour d'une func recursive
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  // si on change le type de retour en Unit, en gros la function ne retourne plus rien

  println(aRepeatedFunction("hello", 3))

  // en gros Scala faire du recursive et oublier les loops

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  println(aBigFunction(10))

  def greeting(name: String, age: Int): String =
    s"Hi, my name is $name and I am $age year old."
  //   "Hi, my name is "+name+" and I am "+age+" year old."

  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else n * factorial((n - 1))
  }

  def fibonacci(n: Int): Int = {
    if (n <= 0) 0
    else if (n == 1) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true else n % t != 0 && isPrimeUntil(t - 1)
    }

    isPrimeUntil(n / 2)
  }

  println(greeting("yann", 34))
  println(factorial(3)) // 6
  println(fibonacci(10)) //55
  println(isPrime(2003))
}
