package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {

  @tailrec
  def trFact(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else trFact(n - 1, n * acc)
  }

  println(trFact(10, 1))
  println(trFact(10))

  def savePicture(format: String = "jpg", width: Int = 1024, height: Int = 768): Unit = println("savePicture")

  savePicture(width = 800, format = "gif") // omg l'anarchie xD
}
