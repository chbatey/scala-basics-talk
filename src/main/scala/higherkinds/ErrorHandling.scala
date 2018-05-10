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

  object Mappable {
    def apply[F[_]](implicit ev: Mappable[F]): Mappable[F] = ev

    implicit class MappableOps[F[_] : Mappable, A](f: F[A]) {
      def map[B](fab: A => B): F[B] = Mappable[F].map(f)(fab)
      def flatMap[B](fab: A => F[B]): F[B] = Mappable[F].flatMap(f)(fab)
    }
  }

  import implicits.PrincipledTypeConversion._
  import Mappable._

  //#abstracted
  abstract class Abstracted[F[_] : Mappable] {
    def serialiseCustomer: Customer => HttpResponse = ???
    def deSerialisePerson: HttpRequest => Person = ???
    def createCustomer: Person => F[Customer] = ???
    def saveCustomer: Customer => F[Customer] = ???
    //#abstracted
    val mappable = Mappable[F]

    //#first-bit
    val first: HttpRequest => F[Customer] =
      deSerialisePerson andThen
        createCustomer
    //#first-bit

    //#full-request
    val fullRequest: HttpRequest => F[HttpResponse] = hr =>
      first(hr)
        .flatMap(saveCustomer)
        .map(serialiseCustomer)

    //#full-request

    {
      val doAFlatMap = (hr: HttpRequest) =>
        mappable.flatMap(first(hr))(saveCustomer)

      val doAMap: HttpResponse => F[HttpResponse] = (hr: HttpResponse) =>
        mappable.map(doAFlatMap(hr))(serialiseCustomer)
    }

    // Or via a for comprehension
    {
      val first: HttpRequest => F[Customer] = deSerialisePerson andThen createCustomer

      val fullRequestForComp: HttpRequest => F[HttpResponse] = (hr: HttpRequest) =>
        for {
          customer <- first(hr)
          _ <- saveCustomer(customer)
        } yield serialiseCustomer(customer)

    }


    //#abstracted
  }
  //#abstracted

  //#usage
  val withAFuture = new Abstracted[Future] {}
  val whatTypeIsThis: HttpRequest => Future[HttpResponse] =
    withAFuture.fullRequest
  //#usage


}
