package com.Deltalake

object Deltalake extends App{

  def vowels(name:String,vowels:String
             ,ignoreCase:Boolean)= {

    for (ch <- name if
    (vowels.contains(
      if (ignoreCase) ch.toLower else ch)))
      yield ch
  }

  val name= "Piyush GUptA"
  val b = vowels(name,"aeiou",true)
  println(b)

  import collection.mutable.ArrayBuffer
  val data= ArrayBuffer("My","Name", "is","Piyush")

  for (ch <- data) println(ch)

   def world_count(s:Array[String])={
     var result = Map[String,Int]()

     for (words <- s){
       result = result+ (words-> (result.getOrElse(words,0)+1))
     }
     result
   }
    val result = world_count(Array("My","name","is","is","piyush"))
    println(result)

  val words = Array("Mary", "had", "a", "little", "lamb", "its", "fleece",
    "was", "white", "as", "snow", "and", "everywhere", "that", "Mary", "went",
    "the", "lamb", "was", "sure", "to", "go")

//   println((words.groupBy(_.length)).toList.toString )

  println("New York".partition(_.isUpper))

  def print_positive(nums:Array[Int]): Array[Int]={
    var result = Array[Int]()
    var count =0
    for (i <- nums)
      {
        if (i > 0) result = result :+ i
        else {
          if (count == 0) {
            count+=1
            result = result :+ i
          }

        }

      }
    result
  }

   var output= print_positive(Array(1,2,3,4,5,-7,-9))
   println(output.toList.toString )


  import collection.mutable.ArrayBuffer
  val array_buffer = ArrayBuffer[Int](5)
  array_buffer+=5
  array_buffer++=List(1,2,3,4,6,7,8,9)

  array_buffer--=List(6,7,8)

  println(array_buffer)

  import collection.mutable.Map

  val students= Map[String,String]()
  students += ("Piyush"-> "Singapore")
  students += ("Akash" -> "Jaipur")

  println(students.keys, students.values)

  students.keys.foreach { i =>
    println("key is: " + i)
    println("value is: " + students(i))
  }

  println(
    if (students.contains("Chandu"))
      println("hello")
    else
      println("bye"))

  val greetStrings:Array[String] = new Array[String](3)

  greetStrings(0)= "piyush"
  greetStrings(1)= "Akash"

  for (i <- 0 to 1)
    println(greetStrings.apply(i))

  val names = new ArrayBuffer[String](1)
  names+="Heaven"
  names+="God"
  names+="Pure"
  println("\n")

  names.foreach(println)

//  define a class

  class Person(val name:String="Piyush"
               , var age:Int=29){
    println("hello")

    def update_age(new_age:Int)={
      this.age = new_age
    }

    def *(factor:Int)={
       this.age = factor*this.age

    }
    override def toString=
      f"Name is ${name} and age is ${age}"
      }

  val p = new Person("Piyush",29)
  println(p.name, p.age)
  p.update_age(35)
  println(p.name, p.age)
  p.*(2)
  print(p.age)


  class Time(var h: Int , var m: Int=0){
    private val minutessincemidnight = h *60 +m
    def hours = minutessincemidnight/60
    def minutes = minutessincemidnight % 60

    if (h<0 || h>23 || m<0 || m>59) throw new IllegalArgumentException

    override def toString: String = f"${h}:${m}"

    def <(other:Time): Boolean = minutessincemidnight < other.minutessincemidnight


  }


  val morning = new Time(9,20)
  val evening = new Time(15,20)
  val before = morning.<(evening)
  println(before)

  println(morning.minutes )


}
