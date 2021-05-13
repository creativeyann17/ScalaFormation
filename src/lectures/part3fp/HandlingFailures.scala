package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailures extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException)

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException

  val potentialFailure = Try(unsafeMethod())

  println(potentialFailure)

  val anotherPotentialFailure = Try {
    // ugly stuff
  }

  def backupMethod(): String = "yep"
  // utilities
  println(potentialFailure.isSuccess)
  println(potentialFailure.orElse(Try(backupMethod())))

  // good practices
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)

  def betterBackupMethod(): Try[String] = Success("better")

  println(betterUnsafeMethod().orElse(betterBackupMethod()))

  // map, flatmap, filter
  println(aSuccess.map(_ * 2))
  // filter peut transformer un success en failure si le filtre trouve rien

  val hostname = "localhost"
  val port = "8080"

  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val rand = new Random(System.nanoTime())
      if (rand.nextBoolean()) "page html"
      else throw new RuntimeException("not connected")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val rand = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (rand.nextBoolean()) new Connection
      else throw new RuntimeException("failed to connect")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  HttpService.getSafeConnection(hostname, port)
    .flatMap(_.getSafe("/index.html")).foreach(renderHTML)

}
