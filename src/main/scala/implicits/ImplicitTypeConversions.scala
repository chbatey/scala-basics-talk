package implicits

import java.util.concurrent.CompletableFuture

import higherkinds.ErrorHandling.Mappable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object ImplicitTypeConversions {

  //#classes
  case class Cat(name: String)
  case class Dog(name: String, status: String)
  //#classes

  //#implicit
  implicit def catToDog(cat: Cat): Dog = Dog(cat.name, "clean")
  //#implicit

  //#clean
  def cleanDog(dog: Dog): Dog =
    dog.copy(status = "clean with hose pipe")
  //#clean

  //#ruby
  val ruby = Cat("Ruby")
  //#ruby

  //#cleaning
  cleanDog(ruby)
  //#cleaning
}

object PrincipledTypeConversion {

  //#future-mappable
  implicit def futureMappable = new Mappable[Future] {
    def map[A, B](fa: Future[A])(f: A => B): Future[B] =
      fa.map(f)
    def flatMap[A, B](fa: Future[A])(f: A => Future[B]): Future[B] =
      fa.flatMap(f)
  }
  //#future-mappable

  //#cf-mappable
  implicit def completableFutureMappable= new Mappable[CompletableFuture] {
    def map[A, B](fa: CompletableFuture[A])(f: A => B): CompletableFuture[B] =
      fa.thenApply(a => f(a))
    def flatMap[A, B](fa: CompletableFuture[A])(f: A => CompletableFuture[B]): CompletableFuture[B] =
      fa.thenCompose(a => f(a))
  }
  //#cf-mappable
}
