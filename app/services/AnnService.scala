package services

import com.hbc.api.neighbors.v0.Anns
import com.hbc.api.neighbors.v0.models._
import com.google.inject.{Inject, Singleton}
import services.AnnService.AnnVector

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AnnService @Inject()(anns: Anns) {

  def getIndexes()(implicit executionContext: ExecutionContext): Future[Seq[String]] = anns.getIndexes()

  def getVector(indexName: String, annKey: AnnKey)(
    implicit executionContext: ExecutionContext): Future[Option[AnnVector]] = {
    for {
      vectorSearchResults <- anns.postIndexesAndVectorsByIndexName(indexName, List(annKey))
      vectorResultOpt = vectorSearchResults.headOption
      vectorOpt = vectorResultOpt.flatMap(_.vector)
    } yield vectorOpt
  }

  def topKNeighboursByVector(index: String, annSearch: AnnSearch)(implicit executionContext: ExecutionContext): Future[Seq[AnnSearchResult]] = {
    anns.postSearchByIndex(index, annSearch: AnnSearch)
  }

}

object AnnService {

  type AnnVector = Seq[BigDecimal]

}