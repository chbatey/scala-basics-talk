package expressions
import scala.collection.immutable

object ControlFlow extends App {

  ifs()
  whiles()
  fors()

  def ifs(): Unit = {
    val x = -10

    {
      var positive: Boolean = false
      if (x > 0) {
        positive = true
      }
    }

    {
      //#if-expression
      if (x > 0) {
        true
      } else {
        false
      }
      //#if-expression
    }

    {
      //#if-assign
      val positive = if (x > 0) {
        true
      } else {
        false
      }
      //#if-assign
    }

    {
      //#if-short
      val positive = if (x > 0) true else false
      //#if-short
    }

    {
      //#if-type
      val positive = if (x > 0) {
        true
      }
      //#if-type
      println(s"Hrmm: $positive ${positive.getClass}")
    }

    {
      //#if-anyval
      val positive: AnyVal = if (x > 0) {
        true
      }
      //#if-anyval
    }
  }

  def whiles(): Unit = {
    val x = 2
    val y = while (x < 2) {
      3
    }
    println(s"While as an expression: $y ${y.getClass}")
  }

  def fors(): Unit = {
    val x: Unit = for (i <- 1 to 4) {
      "cats"
    }

    println(s"For loop: ${x} ${x.getClass}")

    val y: immutable.Seq[Int] = for {
      i <- 1 to 4
    } yield i

    println(s"For comprehension $y ${y.getClass}")

    val z: immutable.Seq[String] = for (
      i <- List("one", "two", "three")
    ) yield i


  }
}
