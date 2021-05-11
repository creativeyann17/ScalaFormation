package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2
  println(x)

  println(2 + 3 * 4)
  System.out.println(2 + 3 * 4) // haha

  println(1 == x)
  println(!(1 == x))

  var aVar = 2
  aVar += 3
  println(aVar)

  val aCondition = true
  val aConditionValue = if (aCondition) 5 else 3 // IF is an expression en scala, ca resemble a une cond ternaire tout le tps
  println(aConditionValue)
  println(if (aCondition) 5 else 3)

  // en gros faut pas faire de while ou de boucle en programmation imperative
  var i = 0
  val aWhile = while (i < 10) {
    println(i)
    i += 1
  }

  val aWeirdValue = (aVar = 3)
  // Unit <=> void
  println(aWeirdValue)
  // side effect = affectation = return Unit

  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "goodbye"
    // si on met une autre valeur sur cette ligne, aCodeBlock sera cette valeur
  }

  println(aCodeBlock)

  val someValue = { // true
    2 < 3
  }

  val someOtherValue = { // 42
    if (someValue) 239 else 986
    42
  }

}
