package lectures.part3fp

import scala.annotation.tailrec
import scala.collection.immutable._

object TuplesAndMaps extends App {

  val aTuple = new Tuple2(1, "foo") // = (1, "foo") fonctionne aussi

  println(aTuple._1)
  println((aTuple._2))
  println(aTuple.copy(_2 = "bar"))
  println(aTuple.swap)

  println("foo" -> "bar")

  val aMap: Map[String, Int] = Map()
  val phoneBook = Map(("Jim", 555), ("JIM", 999), "Daniel" -> 789).withDefaultValue(999)
  println(phoneBook)
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("Yolo")) // throw exception si withDefaultValue pas defini

  val newPairing = "Mary" -> 678
  val newPhonebook = phoneBook + newPairing
  println(newPhonebook)

  // map, flatmap, filter
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  println(phoneBook.filterKeys(_.startsWith("J")))
  println(phoneBook.mapValues(n => n * 10))
  println(phoneBook.toList.toMap)
  val names = List("Bob", "Angela", "James", "Jim");
  println(names.groupBy(name => name.charAt(0))) // SQL like, pas mal pas mal

  // excercise

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], a: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(network, a, friends.head))
    }

    val unfriended = removeAux(network(a), network)
    unfriended - a
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val maryBob = friend(jimBob, "Bob", "Mary")

  println(maryBob)

  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  println(nFriends(maryBob, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }

  println(mostFriends(maryBob))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    // network.filterKeys(k => network(k).size == 0).size
    // network.filter(p => p._2.isEmpty).size
    network.count(_._2.isEmpty)
  }

  println(nPeopleWithNoFriends(maryBob))

  def socialNetwork(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialNetwork(maryBob, "Mary", " Jim"))
  println(socialNetwork(maryBob, "Mary", " Bob"))
}
