package layers

import Layers._

object WebRequestRealistic {

  class DatabaseConnection


  //#data-access-implicit-define
  implicit val dbConnection = new DatabaseConnection
  //#data-access-implicit-define

  //#data-access-pa
  object DataAccess {
    def saveCustomer(databaseConnection: DatabaseConnection)
                    (c: Customer): Customer = ???
  }
  //#data-access-pa

  //#data-access-implicit
  object DatabaseAccessImplicit {
    def saveCustomer(c: Customer)
                    (implicit db: DatabaseConnection): Customer = ???
  }
  //#data-access-implicit

  //#data-access-class
  class DataAccessClass(databaseConnection: DatabaseConnection) {
    def saveCustomer(c: Customer): Customer = ???
  }
  //#data-access-class

  {
    //#data-access-pa-con
    val databaseConnection = new DatabaseConnection
    //#data-access-pa-con

    //#data-access-pa-use
    val saveCustomer: Customer => Customer =
      DataAccess.saveCustomer(dbConnection)
    //#data-access-pa-use
  }

  {
    //#data-access-implicit-use
    val saveCustomer: Customer => Customer =
      DatabaseAccessImplicit.saveCustomer
    //#data-access-implicit-use
  }

  {
    //#data-access-class-use
    val dac = new DataAccessClass(dbConnection)
    val saveCustomer: Customer => Customer = dac.saveCustomer
    //#data-access-class-use
  }

  // TODO show how we'd deal with customer => unit


  // TODO link to doobie
}
