package exercices

import scala.annotation.tailrec

/*
trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A): B
}*/

sealed abstract class MyList[+A] {
  def head: A;

  def tail: MyList[A];

  def isEmpty: Boolean;

  def add[B >: A](element: B): MyList[B];

  protected def printElements: String

  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A => B): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]

  def foreach(f: A => Unit): Unit

  def sort(f: (A, A) => Int): MyList[A]

  def zipWidth[B, C](list: MyList[B], f: (A, B) => C): MyList[C]

  def fold[B](start: B)(operator: (B, A) => B): B

}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  override protected def printElements: String = throw new NoSuchElementException

  override def map[B](transformer: Nothing => B): MyList[B] = Empty

  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def foreach(f: Nothing => Unit): Unit = ()

  override def sort(f: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  override def zipWidth[B, C](list: MyList[B], f: (Nothing, B) => C): MyList[C] = {
    if (!list.isEmpty) throw new RuntimeException("Not same size")
    else Empty
  }

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

final case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

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

  override def map[B](transformer: A => B): MyList[B] = {
    new Cons(transformer.apply(head), t.map(transformer))
  }

  override def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate.apply(head)) new Cons(head, t.filter(predicate))
    else t.filter(predicate)
  }

  override def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer.apply(h) ++ t.flatMap(transformer)
  }

  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons[B](head, t ++ list)

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def sort(f: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (f(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(f)
    insert(h, sortedTail)
  }

  override def zipWidth[B, C](list: MyList[B], f: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Not same size")
    else new Cons[C](f(head, list.head), t.zipWidth(list.tail, f))
  }

  // comme reduce
  override def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start, h))(operator)
  }
}

object ListTest extends App {
  val list = new Cons[Int](1, new Cons(2, new Cons(3, Empty)))
  println(list.tail.head) // 2
  println(list.add(4).head)
  println(list.isEmpty)
  println(list.toString)

  println(list.map((element) => element * 2))

  println(list.filter((element) => element < 2))

  val list3 = new Cons(4, new Cons(5, Empty))
  println(list ++ list3)

  println(list.flatMap((element) => new Cons[Int](element, new Cons[Int](element + 1, Empty))).toString)

  class Stuff

  val list2 = new Cons[String]("1", new Cons("2", new Cons("3", Empty)))
  println(list2.tail.head) // 2
  println(list2.add("4").head)
  println(list2.add(4).toString)
  println(list2.add(new Stuff).toString) // franchement a ce point la javascript a fait le bon choix, n'importe quoi dans un array et puis c'est tout ...
  println(list2.isEmpty)
  println(list2.toString)

  list.foreach(x => println(x))
  println(list.sort((x, y) => y - x))

  println(list.zipWidth(list2, (x: Int, y: String) => x + "/" + y))

  println(list.fold(0)(_ + _))

  var yolo = for {
    x <- list
    y <- list2
  } yield s"$x$y"

  println(yolo)

}
