package controllers

import javax.inject._

import play.api.mvc._
import play.api.Logger
import play.api.libs.json.Json

import scala.concurrent.{ExecutionContext, Future}
import services.{ AnnService }
import com.hbc.api.neighbors.v0.models.{ AnnKey }
import com.hbc.api.neighbors.v0.models.AnnSearch
import com.hbc.api.neighbors.v0.models.json._


@Singleton
class AnnIndexes @Inject()(annService: AnnService, cc: ControllerComponents)(implicit exec: ExecutionContext) extends AbstractController(cc) {

  private val Log = Logger(classOf[AnnIndexes])

  def get = Action.async {

    val futureResults = annService.getIndexes()

    for {
      result <- futureResults
    } yield {
      //grouping by tiefvision by brand and tophat
      val map = result.filter(x => !x.contains("saks") && !x.contains("tophat")).groupBy(x => x.split("-")(0) + "-" + x.split("-")(1)) ++ Map(("generic-saks" -> result.filter(x => x.contains("saks") && x.contains("tophat"))))
      Ok(Json.toJson(map))
    }
  }

  def postByIndex(index: String) = Action.async(parse.json[AnnKey]) { request =>

    val annKey = request.body
    println(annKey)
    for {
      result <- annService.getVector(index,annKey)
    }yield {
      Ok(Json.toJson(result))
    }
  }

  def postSearchByIndex(index: String) = Action.async(parse.json[AnnSearch]) { request =>

    val annSearch = request.body

    for {
      result <-annService.topKNeighboursByVector(index,annSearch)
    } yield {
      Ok(Json.toJson(result))
    }
  }
}