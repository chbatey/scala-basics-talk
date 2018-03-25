package scala.ss

class ControlFlow {

  def ifs(): Unit = {
    val x = 10

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
    }

    {
      //#if-anyval
      val positive: AnyVal = if (x > 0) {
        true
      }
      //#if-anyval
    }
  }
}
