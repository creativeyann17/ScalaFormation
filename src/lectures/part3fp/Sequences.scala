package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  val aSeq = Seq(1, 2, 3, 4)
  println(aSeq)
  println(aSeq.reverse)
  println(aSeq(2))
  println(aSeq ++ Seq(7, 6, 5).sorted)

  val aRange = 1 until 10 // to / until
  aRange.foreach(println)
  (1 to 10).foreach(x => {
    println(x)
  })

  val aList = List(1, 2, 3)
  val prepended = 42 +: aList :+ 10 // +: ou :+
  println(prepended)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-"))

  val numbers = Array(1, 2, 3, 4)
  val threeElems = Array.ofDim[Int](3) // init with some default values, pour les classe ca sera null

  println(numbers.mkString(" "))
  println(threeElems.mkString(" "))

  numbers(2) = 1 // appel numbers.update(2,0) (update est comme apply mais prend une affectation, ok)
  println(numbers(2))

  val numberSq: Seq[Int] = numbers // WrappedArray, implicit conversion
  println(numberSq)

  println("-----------------------------------")

  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val rand = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(rand.nextInt(maxCapacity), rand.nextInt)
      System.nanoTime() - currentTime;
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))

}
