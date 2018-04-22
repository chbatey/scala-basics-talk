package implicits

object Constraints {

  trait Request[Req, Resp] {
    def invoke(in: Req): Resp
    def invoke()(implicit ev: Req =:= Unit): Resp
  }

  abstract class UnitRequest[Resp] extends Request[Unit, Resp] {
    override def invoke(in: Unit): Resp = ???
  }

  val noInput = new Request[Unit, String] {
    def invoke(in: Unit): String = invoke()
    def invoke()(implicit ev: =:=[Unit, Unit]): String = ???
  }

  val requestResponse = new Request[Int, String] {
    def invoke(in: Int): String = ???
    def invoke()(implicit ev: =:=[Int, Unit]): String = ???
  }

//  requestResponse.invoke()

  noInput.invoke()

}

object SomeMath {



}
