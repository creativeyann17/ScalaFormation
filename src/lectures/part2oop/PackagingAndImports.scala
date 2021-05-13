package lectures.part2oop

import lectures.part2oop.OOBasics.Writer

//._ import tout du package
//.{foo, bar, ...}
//.foo => bar pratique si 2 classes ont le meme nom

import java.sql.{Date => SqlDate}
import java.util.Date

object PackagingAndImports extends App {

  val writer = new Writer("food", "bar", 2021)

  sayHello
  println(SPEED_OF_LIGHT)

  println(new Date())
  println(new SqlDate(System.currentTimeMillis()))

  // default imports
  // java.lang String Object Exception
  // scala , Int etc...
}
