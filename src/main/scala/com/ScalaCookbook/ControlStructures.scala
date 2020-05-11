package com.ScalaCookbook

object ControlStructures extends App {

  /*53-96*/

  /********************************************************************
    3.1. Looping with for and foreach
    *******************************************************************/
    val a = Array("apple", "banana", "orange")
    val newArray = for (e <- a) yield {
        val s= e.toUpperCase()
        s
        }
    for (i <- 0 until a.length){
      println(f"${i}: ${a(i)}")
    }

    for ((e,count) <- a.zipWithIndex){
      println(f"${count} is ${e}")
    }

    val names = Map("fname" -> "Robert", "lname" -> "Goren")

    for ((k,v) <- names){
      println(s"${k}-> ${v}")
    }

  /********************************************************************
    3.2. Using for Loops with Multiple Counters
   ********************************************************************/

    for {
        i <- 1 to 2;
        j <- 1 to 2
    } println(f"i=${i}, j=${j}")

  /********************************************************************
    3.3  Using a for Loop with Embedded if Statements (Guards)
    ********************************************************************/
    for (i <- 1 to 20 if (i%5==0)) println(i)

        for {
          i <- 1 to 10 if i > 3
          if i < 6
          if i % 2 == 0
        } println(i)

//       val files = Array("Data.txt", "data.parquet")

//      for (file <- files) {
//        if (hasSoundFileExtension(file) && !soundFileIsLong(file)) {
//          soundFiles += file
//          }
//        }


  /********************************************************************
    3.4. Creating a for Comprehension (for/yield Combination)
    ********************************************************************/
  val students = Array("chris", "ed", "maurice")

  for (name <- students) yield name.capitalize


  /********************************************************************
    3.5. Implementing break and continue
    ********************************************************************/
//    import scala.util.control.Breaks._
//    for (i <- 1 to 10){
//      println(i)
//      if (i> 4) break
//    }

  /********************************************************************
    3.7. Using a Match Expression Like a switch Statement
    ********************************************************************/
    import scala.annotation.switch

    val first = "1"
    var position1 = first match {
    case "1" => "First"
    case "2" => "Second"
    case _ => "Other"
  }
    val i = 8
    var position2 = (i: @switch) match {
      case 1 => "First"
      case 2 => "Second"
      case _ => "Other"
    }
    println(i,position1,position2)


  /********************************************************************
    3.8. Matching Multiple Conditions with One Case Statement
    ********************************************************************/
    val cmd = "stop"
    cmd match {
      case "start" | "go" => println("starting")
      case "stop" | "quit" | "exit" => println("stopping")
      case _ => println("doing nothing")
    }

  /********************************************************************
    3.9. Assigning the Result of a Match Expression to a Variable
    ********************************************************************/

      def isTrue(a: Any) = a match {
        case 0 | "" => false
        case _ => true
      }

  /********************************************************************
    3.10. Accessing the Value of the Default Case in a Match Expression
    ********************************************************************/
    i match {
    case 0 => println("1")
    case 1 => println("2")
    case default => println("You gave me: " + default)
      }

  /********************************************************************
    3.11. Using Pattern Matching in Match Expressions
    ********************************************************************/
//  case List(0, _, _) => "a three-element list with 0 as the first element"
//  case List(1, _*) => "a list beginning with 1, having any number of elements"
//  case Vector(1, _*) => "a vector beginning with 1 and having any number ..."
//


  /********************************************************************
    3.13. Adding if Expressions (Guards) to Case Statements
    ********************************************************************/

  i match {
    case a if 0 to 9 contains a => println("0-9 range: " + a)
    case b if 10 to 19 contains b => println("10-19 range: " + b)
    case c if 20 to 29 contains c => println("20-29 range: " + c)
    case _ => println("Hmmm...")
  }


  /********************************************************************
    3.16. Matching One or More Exceptions with try/catch
    ********************************************************************/
//        val s = "Foo"
//          try {
//          val i = s.toInt } catch {
//          case e: Exception => e.printStackTrace }
//
//      try
//      { openAndReadAFile(filename)
//      } catch {
//        case e: FileNotFoundException => println("Couldn't find that file.")
//        case e: IOException => println("Had an IOException trying to read that file")




}
