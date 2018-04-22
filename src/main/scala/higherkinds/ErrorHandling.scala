package higherkinds

import layers.Layers.{Customer, Person}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object ErrorHandling {

  import layers.Layers.{HttpRequest, HttpResponse}

  //#web-request
  type WebRequest = HttpRequest => Future[HttpResponse]
  //#web-request

  type Error = String
  //#web-request-2
  type WebRequest2 = HttpRequest => Either[Error, HttpResponse]
  //#web-request-2


  trait NotAbstracted {
    //#serialize-func
    def serialiseCustomer: Customer => HttpResponse = ???
    //#serialize-func

    //#serialize-func
    def deSerialisePerson: HttpRequest => Person = ???
    //#serialize-func

    //#customer-func
    def createCustomer: Person => Future[Customer] = ???
    //#customer-func

    //#db-func
    def saveCustomer: Customer => Future[Customer] = ???
    //#db-func

    //#deserialise-create
    val fstStep: HttpRequest => Future[Customer] =
      deSerialisePerson andThen
        createCustomer
    //#deserialise-create

    //#save
    val customerSaved: HttpRequest => Future[Customer] = hr =>
        fstStep(hr).flatMap(saveCustomer)
    //#save

    //#full
    val fullRequest: HttpRequest => Future[HttpResponse] = hr =>
      customerSaved(hr).map(serialiseCustomer)
    //#full


    //#full2
    val fullRequest2: HttpRequest => Future[HttpResponse] = hr => {
      val person: Person = deSerialisePerson(hr)
      for {
        customer <- createCustomer(person)
        _ <- saveCustomer(customer)
      } yield serialiseCustomer(customer)
    }
    //#full2
  }

  //#mappable
  trait Mappable[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
    def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
  }
  //#mappable

  //#abstracted
  class Abstracted[F[_] : Mappable] {
    def serialiseCustomer: Customer => HttpResponse = ???
    def deSerialisePerson: HttpRequest => Person = ???
    def createCustomer: Person => F[Customer] = ???
    def saveCustomer: Customer => F[Customer] = ???
  }
  //#abstracted

  //#full-request-function
  //  val registerCustomer: WebRequest =
  //    deSerialisePerson andThen
  //      createCustomer andThen
  //      saveCustomer andThen
  //      serialiseCustomer
  //#full-request-function

}
