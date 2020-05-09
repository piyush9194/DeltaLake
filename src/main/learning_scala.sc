//val a = "Piyush Gupta"
//
//println(a)
//var answer = 6 *7
//answer
//var greeting:String = null
//greeting  = "Piyush"
//1.to(10)
//1 to 10
//"Hello".intersect("World")
//val x:Int= 20
//x*x*x
//val result = 1.+(10)
//"Hello".length
//"Hello".distinct
//
//val b:BigInt=21
//b.pow(1000)


//import scala.math._
//sqrt(10)
//
//
//1.to(10)
//
//1.to(10).map(sqrt(_))
//
//6.*(7)
//
//"Rhine".permutations.toArray
//
//"Missiiipiii".distinct
//
//
//"ABC".sum

//
//val n = 10
//
//
//for (i <- 1 to n)
//{
//  println(i)
//}
//
//
//for (i<- 1 to 3; j <- 1 to 3 if i==j)
//{
//  print(i,j)
//}
//
//
//for (i <- 1 to 3) yield (i*i)
//
//
//def abs(x:Double)={
//  if (x>=0) x else -x
//}
//
//def fact(n:Int):Int={
//  if (n<=0) 1
//  else n * fact(n-1)
//
//}



def sum(args:Int*)={
  var result =0
  for (arg <- args)
    result+=arg
  result

}

val s = sum(1,2,3,4,5,6)
val vowels = Array('a','e','i','o','u)


def vowel(ch:Char)={
  val vowels = Array('a','e','i','o','u')
  print(ch)
  vowels contains(ch)

}

val b = vowel('m')


def extract_vowels(data:String)={
  var result = ""
  for (i <- data) {
    if ("aeiou".contains(i)){
      result = result + i
    }
  }
  result
}

val m = extract_vowels("Piyush")



def vowels2(data:String)=
  for (ch <- data if "aeiou".contains(ch)) yield ch



val k = vowels2("Piyush Gupta")

def recursive_vowel(data:String):String= {
  if (data.length() == 0) ""

  else {
    val ch = data(0)
    val rest = recursive_vowel(data.substring(1))
    if ("aeiou".contains(ch)) ch + rest else rest
  }
}

val h=recursive_vowel("Piyush")



def whilevowel(s:String):String={
  var i =0
  var result = ""
  while (i<s.length()){
    if ("aeiou".contains(s(i))){
      result += s(i)
    }
    i = i+1
  }
  result
}


val po = whilevowel("missiiiouuu")




def alienvowel(s:String,vowel:String,
               ignorecase:Boolean):String={
  var result = ""
  for (ch <- s) {
    var char= ch
    if (ignorecase) char=ch.toLower
    if (vowel.contains(char))
      result+=ch
    }
  result
}


val alien = alienvowel("Piyush GUpta"
  ,"ushta",true)


def fibonacci(n:Int):Int={
  if (n<=1) n
  else fibonacci(n-1) + fibonacci(n-2)
}

val fib = fibonacci(7)

import scala.collection.mutable.ArrayBuffer



def removeAllbutFirst(a:ArrayBuffer[Int])= {
  for (i <- (for (i<- 0 until (a.length) if (a(i)<0)) yield i).drop(1).reverse) a.remove(i)
}


val data = ArrayBuffer(1, 2, -3, 4, 5, -5, -6)
removeAllbutFirst(data)
data