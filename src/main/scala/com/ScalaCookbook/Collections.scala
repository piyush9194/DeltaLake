package com.ScalaCookbook
import collection.mutable.ArrayBuffer
import collection.mutable.ListBuffer


object Collections extends App{
    val students = Array("Piyush")
    println(students.toList.toString())

    /*************** Second Way****************************
    Collection methods like filter, foreach, map,reduceLeft,
    and many more have loops built into their algorithms.
    ******************************************************/

    /******************************************************
     function to return even numbers
     ******************************************************/

    def even(data:ListBuffer[Int]):ListBuffer[Int]=
      data.filter(_%2==0)
     /*************** Second Way*********************************************************************
     for(i <- data if (i%2 == 0)) yield i
       *********************************************************************************************/

    println(even(ListBuffer(1,2,3,4,5,6,6,8,9)).toList.toString())

    /*************************************************************************************************
        10.1. Understanding the Collections Hierarchy
      ***********************************************************************************************/

    val v = Vector(1,2,3,4)
    println(v.sum, v.filter(_>1), v.map(_*2))

    /************************************************************************************************
          Sequences branch off into two main categories:
        1. indexed sequences
        2. linear sequences (linked lists)
      *********************************************************************************************/

    val seq = scala.collection.immutable.LinearSeq(1,2,3)

    /***********************************************************************************************
      Scala Map is a collection of key/value pairs,
      where all the keys must be unique
      Mutable and Immutable maps
      *********************************************************************************************/

    val a = Map("Piyush" -> "Singapore")
    val b = collection.mutable.Map("Piyush" -> "Singapore")

    /*********************************************************************************************
      Scala Set is a collection of unique elements
     *********************************************************************************************/

    val set = Set(1,2,3,3)
    val s = collection.mutable.Set(1,2,3,4,3,2,4,8)
    s.add(23)
    println(set,s)

  /***********************************************************************************************
    10.2. Choosing a Collection Class Problem
    You want to choose a Scala collection class to solve a particular problem.

      There are three main categories of collection classes to choose from:
          • Sequence
          • Map
          • Set

    When choosing a sequence (a sequential collection of elements), you have two main decisions:
        1. Should the sequence be indexed (like an array), allowing rapid access to any elements,
          or should it be implemented as a linked list?
        2. Do you want a mutable or immutable collection?

    Main Immutable sequence choices

    Linear Seq==>List, Queue, Stack, Stream(Lazy and persistent)
    Indexed Sequence===>Range,String,Vector

     Main Mutable sequence choices

    Linear Seq        DoubleLinkedList, LinkedList, ListBuffer, MutableList,Queue,Stack
    Indexed Sequence  Array, ArrayBuffer, ArrayStack, StringBuilder

    IndexedSeq    Implies that random access of elements is efficient.
    LinearSeq     Implies that linear access to elements is efficient.
    Seq           Used when it isn’t important to indicate that the
                  sequence is indexed or linear in nature.

    Map,SortedMap, HashMap
    Set, SortedSet

    Tuple: Supports a heterogeneous collection of elements.
          There is no one “Tuple” class; tuples are implemented as case classes
          ranging from Tuple1 to Tuple22, which support 1 to 22 elements.
          Accessed via ._1, ._2 and so on.

    All of the collection classes except Stream are strict, but the other collection classes
    can be converted to a lazy collection by creating a view on the collection.

    *************************************************************************************************/


    /************************************************************************************************
    10.3. Choosing a Collection Method to Solve a Problem

      Filtering methods*******************************
      Methods that can be used to filter a collection include::::::

      collect, diff, distinct, drop, dropWhile, filter, filterNot,
      find, foldLeft, foldRight, head, headOption, init, intersect,
      last, lastOption, reduceLeft, reduceRight, remove, slice, tail,
      take, takeWhile, and union.

      Transformer methods*******************************
      Transformer methods take at least one input collection to create a
      new output collection, typically using an algorithm you provide.
      They include ::::::::

      +, ++, −, −−, diff, distinct, collect, flatMap, map,
      reverse, sortWith, takeWhile, zip, and zipWithIndex.

      Grouping methods*******************************
      These methods let you take an existing collection and create multiple
      groups from that one collection. These methods include :::::::::

      groupBy, partition, sliding, span, splitAt, and unzip.

      flatten: flatten converts a list of lists down to one list;
      foreach is like a for loop, letting you iterate over the elements in a collection

      *********************************************************************************************/


    var z = ArrayBuffer(1,2,3,4,5,6,7,8)
    println(
        z.count(_>4),
        z.filter(_>4),
        z.map(x=> x*2)
      )
    z.foreach(_*2)
    println(z)
    z+=2
    val employee = ArrayBuffer("Piyush","Pankaj","Akash","Chandu")

    val m = employee.groupBy(_.charAt(0))
    println(m)


    /***********************************************************************************************
    10.5. Declaring a Type When Creating a Collection
      *********************************************************************************************/

    val x = Array[Number](1,2,3,4)
    val cities = ArrayBuffer[String]()

  /*************************************************************************************************
    10.6. Understanding Mutable Variables with Immutable

    When you first start working with Scala, the behavior of a mutable variable with
    an immutable collection can be surprising. To be clear about variables:

        • A mutable variable (var) can be reassigned to point at new data.
        • An immutable variable (val) is like a final variable in Java; it can never be
          reassigned.
            To be clear about collections:
        • The elements in a mutable collection (like ArrayBuffer) can be changed.
        • The elements in an immutable collection (like Vector) cannot be changed
  ************************************************************************************************/


  /***********************************************************************************************
  10.7. Make Vector Your “Go To” Immutable Sequence
    *********************************************************************************************/

  val vec = Vector("a", "b", "c")

  /***********************************************************************************************
        10.8. Make ArrayBuffer Your “Go To” Mutable Sequence ArrayBuffer is an indexed sequential
              collection.
              Use ListBuffer if you prefer a linear sequential collection that is mutable

   If you need a mutable sequential collection that works more like a List (i.e., a linear sequence
   rather than an indexed sequence), use ListBuffer instead of ArrayBuffer.
   The Scala documentation on the ListBuffer states, “A ListBuffer is like an array buffer except
   that it uses a linked list internally instead of an array. If you plan to convert the buffer
   to a list once it is built up, use a list buffer instead of an array buffer.”
    *********************************************************************************************/

    val nums = ArrayBuffer[Int](1,2,3,4,5)
    nums += 10
    nums += (6,8)
    nums -= 3
    println(nums)

  /***********************************************************************************************
      10.9. Looping over a Collection with foreach
   **********************************************************************************************/

    nums.foreach(x => println(x*x))
    nums.foreach(println(_))
    "Piyush".foreach(println)

    val longwords = ArrayBuffer[String]()
    "My name is Piyush Gupta".split(" ").foreach{
            e => if (e.length >4) longwords.append(e)
                else println(f"Not Added: ${e} ")
            }
    println(f"Long words are: ${longwords}")

    /********************************************************************************************
      Access the members of map via foreach in tuple form
      ********************************************************************************************/

    val singer = Map("fname" -> "Tyler", "lname" -> "LeDude")
    singer.foreach(x => println(x,x._1,x._2))

    /*********************************************************************************************
      10.10. Looping over a Collection with a for Loop
              To build a new collection from an input collection, use the for/yield construct.
    **********************************************************************************************/

    val fruits = Array("apple", "banana", "orange")
    val newArray = for (e <- fruits) yield e.toUpperCase

    def upper_reverse(s:String)= s.toUpperCase.reverse
    for (i <- fruits) yield upper_reverse(i)

    val names = Map("fname" -> "Ed", "lname" -> "Chigliak")
    for ((k,v) <- names) println(f"key is ${k} and value is ${v}")

  /**********************************************************************************************
    * 10.11. Using zipWithIndex or zip to Create Loop Counters
    * You want to loop over a sequential collection, and you’d like to have access to a counter
    * in the loop, without having to manually create a counter.
    *********************************************************************************************/

    val days = Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    days.zipWithIndex.foreach{
    case(day,count) => println(f"${day}, ${count}")
      }

    for ((day, count) <- days.zipWithIndex) {
        println(s"$count is $day")
        }

    val it = Iterator(1,2,3)
    it.foreach(println)


    /**********************************************************************************************
      10.13. Transforming One Collection to Another with for/ yield

      *************** Using guards ***************
      When you add guards to a for comprehension and want to write it as a multiline expression, the
      recommended coding style is to use curly braces rather than parentheses:
        for {
              file <- files
              if hasSoundFileExtension(file)
              if !soundFileIsLong(file)
             } yield file
      **********************************************************************************************/


    /************************************************************************************************
      10.14. Transforming One Collection to Another with map
    *************************************************************************************************/

    val helpers = Vector("adam", "kim", "melissa")
    helpers.map(_.capitalize)
    helpers.map(_.length)
    helpers.filter(_!=" ")


    val sn = " eggs, milk, butter, Coco Puffs "
    val items = sn.split(",").map(_.trim)


    /****************************************************************************************************
        10.15. Flattening a List of Lists with flatten. You have a list of lists (a sequence of sequences)
        and want to create one list (sequence) from them.
    *****************************************************************************************************/

    val lol = List(List(1,2), List(3,4))
    println(lol.flatten)

    val myFriends = List("Adam", "David", "Frank")
    val adamsFriends = List("Nick K", "Bill M")
    val davidsFriends = List("Becca G", "Kenny D", "Bill M")
    val friendsOfFriends = List(adamsFriends, davidsFriends)

    friendsOfFriends.flatten.distinct


  /*********************************************************************************************************
    10.16. Combining map and flatten with flatMap
  *********************************************************************************************************/



}
