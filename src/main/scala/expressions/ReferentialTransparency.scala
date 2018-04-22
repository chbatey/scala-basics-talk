package expressions

object ReferentialTransparency {

  //#x
  val x: Int = ???
  //#x

  //#y
  val y = x * x
  //#y

  {
    //#x-good
    val x = 4
    //#x-good
  }
  {
    //#x-bad
    val x = { println("haha"); 4}
    //#x-bad
  }

}
