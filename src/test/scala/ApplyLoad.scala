import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ApplyLoad extends Simulation {
  val httpConf = http.baseURL("http://localhost:8083")

  // Each simulated user performs a request every second:
  val scn = scenario("Basic load")
    .exec(http("request").get("/file/medium.bin"))

  // Simulate users:
  setUp(scn.inject(rampUsers(40).over(10.seconds))
    .protocols(httpConf))

//  setUp(scn.inject(constantUsersPerSec(400).during(10.seconds))
//    .protocols(httpConf))

}
