package lectures.part2oop

object Exceptions extends App {
  var x: String = null
  // println(x.length) // NPE

  // val aWeirdValue: String = throw new NullPointerException


  def getInt(withException: Boolean): Int = if (withException) throw new RuntimeException("No Int for you") else 42

  val potentialFail = try {
    getInt(false)
    getInt(true)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // finally doesn't influence the return of try (only side effect)
    println("Always")
  }

  println(potentialFail)

  // custom exceptions
  class MyException extends Exception

  val exception = new MyException

  // throw exception

  // throw new OutOfMemoryError()
  // val array = Array.ofDim(Int.MaxValue)

  // throw new StackOverflowError()
  // def infinite: Int = 1 + infinite
  // val noLimit = infinite

  class OverflowException extends Exception

  class UnderflowException extends Exception

  class MathCalculationException extends Exception("Division by 0")

  // serait cool pour faire un TU
  object PocketCalculator {

    /*private def validate(x: Int, y: Int, res: Int): Int = {
      if (x > 0 && y > 0 && res < 0) throw new OverflowException
      else if (x < 0 && y < 0 && res > 0) throw new UnderflowException
      else res
    }*/

    def add(x: Int, y: Int) = {
      val res = x + y
      if (x > 0 && y > 0 && res < 0) throw new OverflowException
      else if (x < 0 && y < 0 && res > 0) throw new UnderflowException
      else res
    }

    def subtract(x: Int, y: Int) = {
      val res = x - y
      if (x > 0 && y < 0 && res < 0) throw new OverflowException
      else if (x < 0 && y > 0 && res > 0) throw new UnderflowException
      else res
    }

    def multiply(x: Int, y: Int) = {
      val res = x * y
      if (x > 0 && y > 0 && res < 0) throw new OverflowException
      else if (x < 0 && y < 0 && res < 0) throw new OverflowException
      else if (x > 0 && y < 0 && res > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && res > 0) throw new UnderflowException
      else res
    }

    def devide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

  //println(PocketCalculator.add(Int.MaxValue, 1))
  println(PocketCalculator.devide(2, 0))

}
