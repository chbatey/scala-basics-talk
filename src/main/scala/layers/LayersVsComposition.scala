package layers


object Layers {
  //#data
  case class Person(name: String)
  case class Customer(customerNumber: Int, p: Person)
  //#data

  type HttpRequest = String
  type HttpResponse = String

  //#web-request
  type WebRequest = HttpRequest => HttpResponse
  //#web-request

  //#serialisation
  trait Serialiser {
    def serialise(c: Customer): HttpResponse
    def deserialie(input: HttpRequest): Person
  }
  //#serialisation

  //#controller
  class WebController(
                       service: ApplicationService,
                       serialiser: Serialiser) {
    def register(p: HttpRequest): HttpResponse = {
      val person = serialiser.deserialie(p)
      val c = service.registerCustomer(person)
      serialiser.serialise(c)
    }
  }
  //#controller

  //#service
  class ApplicationService(dataStore: CustomerRepo) {

    def registerCustomer(p: Person): Customer = {
      // validate them?
      // Generate a unique customer number?
      val c = Customer(1, p)
      dataStore.saveCustomer(c)
      c
    }
  }
  //#service

  //#repo
  trait CustomerRepo {
    def saveCustomer(p: Customer): Unit
  }
  //#repo

}

object OutWithTheLayers {
  object FunctionalLayers {
    case class Person(name: String)
    case class Customer(customerNumber: Int, p: Person)

    type HttpRequest = String
    type HttpResponse = String
    type WebRequest = HttpRequest => HttpResponse

    val serialiseCustomer: Customer => String = ???
    val deSerialisePerson: String => Person = ???

    def createCustomer(p: Person): Customer = {
      // remove the saving logic
      Customer(1, p)
    }

    def saveCustomer(c: Customer): Customer = {
      // do DB stuff, we'll deal with failure in a later post
      c
    }

    val registerCustomer: WebRequest =
      deSerialisePerson andThen
        createCustomer andThen
        saveCustomer andThen
        serialiseCustomer
  }

}

object FunctionComposition {

  def add(x: Int, y: Int) = x + y

  def add10 = (x: Int) => add(x, 10)

  val add10Curried = (add _).curried(10)

  def addCurried(x: Int)(y: Int): Int = x + y

  val whatTypeAmI = addCurried(10) _
}