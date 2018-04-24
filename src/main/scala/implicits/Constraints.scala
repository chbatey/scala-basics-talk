package implicits

import com.sun.xml.internal.ws.client.sei.ResponseBuilder

import scala.concurrent.Future
import scala.concurrent.duration.{Duration, FiniteDuration}
import scala.concurrent.duration._

object Alternatives {

  //#request-before
  trait Request[Req, Resp] {
    def invoke(in: Req): Future[Resp]
  }
  //#request-before

  //#request-before-unit
  val noParamRequest = new Request[Unit, String] {
    def invoke(in: Unit): Future[String] = ???
  }
  //#request-before-unit

  //#request-before-no-param
  trait NoParamRequest[Resp] {
    def invoke(): Future[Resp]
  }
  //#request-before-no-param


  val noParamRequest2 = new Request[Unit, String] {
    def invoke(in: Unit): Future[String] = ???
  }

}

object Constraints {


  //#request-type
  trait Request[Req, Resp] {
    def invoke(in: Req): Future[Resp]
    def invoke()(implicit ev: Req =:= Unit): Future[Resp]
    //#request-type
    = throw new UnsupportedOperationException()
    //#request-type
  }
  //#request-type

  case class RequestBuilder[Req, Resp](
                                        timeout: Duration = Duration.Inf,
                                        asyncHandler: Option[Req => Future[Resp]] = None,
                                        blockingHandler: Option[Req => Resp] = None) {
    def withTimeout(timeout: FiniteDuration): RequestBuilder[Req, Resp] = copy(timeout = timeout)
    def withAsyncHandler(f: Req => Future[Resp]): RequestBuilder[Req, Resp] = copy(asyncHandler = Some(f))
    def withCircuitBreaker(): RequestBuilder[Req, Resp] = this
    def withBlockingHandler(f: Req => Resp): RequestBuilder[Req, Resp] = copy(blockingHandler = Some(f))
    def build(): Request[Req, Resp] = {
      new AsyncRequest(asyncHandler.get)
    }
  }

  class AsyncRequest[Req, Resp](f: Req => Future[Resp]) extends Request[Req, Resp] {
    override def invoke(in: Req) = ???
    override def invoke()(implicit ev: =:=[Req, Unit]) = ???
  }

  //#api-example
  val liftedCall: Request[String, Int] =
    RequestBuilder[String, Int]()
      .withTimeout(10.seconds)
      .withCircuitBreaker()
      .build()
  //#api-example

  //#execute
  liftedCall.invoke("go go gadget go")
  //#execute



  /*
   //#no-compile
   liftedCall.invoke()
   //#no-compile
     */

  def takesRequest[Out](request: Request[Unit, Out]): Future[Out] = {
    request.invoke()
  }

  def takesRequest[In, Out](request: Request[In, Out], in: In): Future[Out] = {
    request.invoke(in)
  }
}

