package exercices

import scala.annotation.tailrec

trait MyPredicate[T] {
  def test(element: T): Boolean
}

trait MyTransformer[A, B] {
  def transform(element: A): B
}

sealed abstract class MyList[+A] {
  def head: A;

  def tail: MyList[A];

  def isEmpty: Boolean;

  def add[B >: A](element: B): MyList[B];

  protected def printElements: String

  override def toString: String = "[" + printElements + "]"

  def map[A, B](transformer: MyTransformer[A, B]): MyList[B]

  def filter[A](predicate: MyPredicate[A]): MyList[A]

  def flatMap[A, B](transformer: MyTransformer[A, B]): MyList[B]

}

object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  override protected def printElements: String = throw new NoSuchElementException

  override def map[A, B](transformer: MyTransformer[A, B]): MyList[Nothing] = throw new NoSuchElementException

  override def filter[A](predicate: MyPredicate[A]): MyList[Nothing] = throw new NoSuchElementException

  override def flatMap[A, B](transformer: MyTransformer[A, B]): MyList[Nothing] = throw new NoSuchElementException
}

final class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String = {
    val separator = ", "

    @tailrec
    def concat(list: MyList[A], all: String): String = {
      if (list.isEmpty) all
      else concat(list.tail, all + separator + list.head)
    }

    concat(this, "").replaceFirst(separator, "")
  }

  override def map[A, B](transformer: MyTransformer[A, B]): MyList[B] = ???

  override def filter(predicate: MyPredicate[A]): MyList[A] = {
    def foo(list: MyList[A], filtered: MyList[A]): MyList[A] = {
      if (list.isEmpty) filtered
      else if (predicate.test(head)) foo(tail, filtered.add(head))
      else foo(tail, filtered)
    }

    foo(this, new Cons[A](, Empty))

  }

  override def flatMap[A, B](transformer: MyTransformer[A, B]): MyList[B] = ???
}

object ListTest extends App {
  val list = new Cons[Int](1, new Cons(2, new Cons(3, Empty)))
  println(list.tail.head) // 2
  println(list.add(4).head)
  println(list.isEmpty)
  println(list.toString)

  class Stuff

  val list2 = new Cons[String]("1", new Cons("2", new Cons("3", Empty)))
  println(list2.tail.head) // 2
  println(list2.add("4").head)
  println(list2.add(4).toString)
  println(list2.add(new Stuff).toString) // franchement a ce point la javascript a fait le bon choix, n'importe quoi dans un array et puis c'est tout ...
  println(list2.isEmpty)
  println(list2.toString)
}
