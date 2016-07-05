package detalcare

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.ExceptionHandler
import akka.stream.ActorMaterializer
import de.heikoseeberger.akkahttpjson4s._
import detalcare.model.{Diagnosis, SerializableToothTest, ToothTest}
import org.json4s.{DefaultFormats, jackson}
import scala.collection.JavaConversions._

object DiagnosticApp extends App with Json4sSupport {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val ruleEngine = new RuleEngine

  implicit val serialization = jackson.Serialization // or native.Serialization
  implicit val formats = DefaultFormats

  implicit def myExceptionHandler: ExceptionHandler = ExceptionHandler {
    case e: Exception =>
      extractUri { uri =>
        println(e.getMessage)
        println(s"Request to $uri could not be handled normally")
        complete(HttpResponse(InternalServerError, entity = "Bad numbers, bad result!!!"))
      }
  }


  val route =
    path("") {
      get {
        getFromResource("index.html")
      } ~
      (post & entity(as[SerializableToothTest])) { serializableToothTest =>
        val toothTest = ToothTest(
          serializableToothTest.coloration,
          serializableToothTest.kindOfPains,
          serializableToothTest.coldStimulus,
          serializableToothTest.heatStimulus,
          serializableToothTest.electricalStimulation,
          serializableToothTest.percussionStimulation,
          serializableToothTest.pulpState,
          serializableToothTest.patientAge
        )
        val found = ruleEngine.analyze(List(toothTest), "KB-ToothDiagnosis.drl")
        val diagnosis = found collect { case x: Diagnosis => x } head

        complete(Diagnosis.translate(diagnosis))
      }
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

}

