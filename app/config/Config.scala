package config

import com.hbc.api.neighbors.v0.{Anns, Client => ApiNeighborsClient}
import com.google.inject.{AbstractModule, Inject, Singleton}
import play.api.inject.ApplicationLifecycle
import play.api.{Configuration, Environment, Logger}
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import akka.stream.Materializer
import akka.stream.ActorMaterializer

class Config (environment: Environment, configuration: Configuration) extends AbstractModule {

  override def configure(): Unit = {

    import play.api.libs.ws.ahc._
    import akka.actor.ActorSystem
    implicit val actorSystem = ActorSystem()
    implicit val mat: akka.stream.Materializer = ActorMaterializer()
    val wsClient = AhcWSClient()

    bind(classOf[Anns]).toInstance(new ApiNeighborsClient(wsClient,"http://api-neighbors.staging.hbcml.private.hbc.com").anns)

  }
}