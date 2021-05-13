package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  def unsafeMethod(): String = null

  //  val res = Some(unsafeMethod()) // beurk
  val res = Option(unsafeMethod()) // Optional.ofNUllable(...)

  println(res)

  def backup(): String = "not null"

  println(Option(unsafeMethod()).orElse(Option(backup())))

  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("yep yep")

  val betterRes = betterUnsafeMethod() orElse (betterBackupMethod())

  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // NPE si null
  println(noOption.getOrElse(10))

  // map, filter  flatmap

  val config: Map[String, String] = Map(
    "host" -> "127.0.0.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "connected"
  }

  object Connection {
    val rand = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
      if (rand.nextBoolean()) Some(new Connection)
      else None
    }
  }

  // try to establish connection
  // my solution
  val host: Option[String] = config.get("host")
  val port: Option[String] = config.get("port")
  if (host.isDefined && port.isDefined) {
    val conn = Connection.apply(host.get, port.get)
    if (conn.isDefined) {
      println(conn.get.connect)
    } else {
      println("failed to connect")
    }
  } else {
    println("Missing config")
  }
  // the author's solution, j'etais loins ....
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  val connectionStatus = connection.map(c => c.connect)
  println(connectionStatus)
  connectionStatus.foreach(println)
  // autre solution

  config.get("host")
    .flatMap(h => config.get("port")
      .flatMap(p => Connection(h, p)))
    .map(c => c.connect)
    .foreach(println)

  val status = for {
    h <- config.get("host")
    p <- config.get("port")
    c <- Connection.apply(h, p)
  } yield c.connect
  println(status)

}
