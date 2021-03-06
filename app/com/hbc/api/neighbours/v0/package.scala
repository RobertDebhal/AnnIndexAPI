/**
  * Generated by API Builder - https://www.apibuilder.io
  * Service version: 0.1.0
  * apibuilder 0.14.3 app.apibuilder.io/hbc/api-neighbors/0.1.0/play_2_6_client
  */
package com.hbc.api.neighbors.v0.models {

  sealed trait AnnKey extends _root_.scala.Product with _root_.scala.Serializable

  /**
    * Defines the valid ann_key_type values for the type AnnKey
    */
  sealed trait AnnKeyAnnKeyType extends _root_.scala.Product with _root_.scala.Serializable

  object AnnKeyAnnKeyType {

    case object LongAnnKey extends AnnKeyAnnKeyType { override def toString = "LongAnnKey" }
    case object StringAnnKey extends AnnKeyAnnKeyType { override def toString = "StringAnnKey" }
    case object UuidAnnKey extends AnnKeyAnnKeyType { override def toString = "UuidAnnKey" }

    final case class UNDEFINED(override val toString: String) extends AnnKeyAnnKeyType

    val all: scala.List[AnnKeyAnnKeyType] = scala.List(LongAnnKey, StringAnnKey, UuidAnnKey)

    private[this] val byName: Map[String, AnnKeyAnnKeyType] = all.map(x => x.toString.toLowerCase -> x).toMap

    def apply(value: String): AnnKeyAnnKeyType = fromString(value).getOrElse(UNDEFINED(value))

    def fromString(value: String): _root_.scala.Option[AnnKeyAnnKeyType] = byName.get(value.toLowerCase)

  }

  final case class LongAnnKey(
                               id: Long
                             ) extends AnnKey

  final case class StringAnnKey(
                                 id: String
                               ) extends AnnKey

  final case class UuidAnnKey(
                               id: _root_.java.util.UUID
                             ) extends AnnKey

  /**
    * Neighbor and its score
    *
    * @param id Id of the neighbor
    * @param score Score between this item and the queried one
    */
  final case class Ann(
                        id: com.hbc.api.neighbors.v0.models.AnnKey,
                        score: BigDecimal
                      )

  /**
    * Search for approximate nearest neighbors
    *
    * @param k Amount of neighbors requested, if query by id, the first result should be the
    *        query itself so k-1 results will be returned
    * @param ids Ids to search for
    * @param vectors Vectors to search for
    */
  final case class AnnSearch(
                              k: Int,
                              ids: _root_.scala.Option[Seq[com.hbc.api.neighbors.v0.models.AnnKey]] = None,
                              vectors: _root_.scala.Option[Seq[Seq[BigDecimal]]] = None,
                              searchK: Int = -1
                            )

  /**
    * Approximate nearest neighbors found
    *
    * @param id Searched id
    * @param vector Searched vector
    * @param neighbors Found neighbors
    */
  final case class AnnSearchResult(
                                    id: _root_.scala.Option[com.hbc.api.neighbors.v0.models.AnnKey] = None,
                                    vector: Seq[BigDecimal],
                                    neighbors: Seq[com.hbc.api.neighbors.v0.models.Ann]
                                  )

  final case class Healthcheck(
                                status: String
                              )

  /**
    * result of the search vector by id
    */
  final case class VectorSearchResult(
                                       id: com.hbc.api.neighbors.v0.models.AnnKey,
                                       vector: _root_.scala.Option[Seq[BigDecimal]] = None
                                     )

  /**
    * Provides future compatibility in clients - in the future, when a type is added
    * to the union AnnKey, it will need to be handled in the client code. This
    * implementation will deserialize these future types as an instance of this class.
    *
    * @param description Information about the type that we received that is undefined in this version of
    *        the client.
    */
  final case class AnnKeyUndefinedType(
                                        description: String
                                      ) extends AnnKey

}

package com.hbc.api.neighbors.v0.models {

  package object json {
    import play.api.libs.json.__
    import play.api.libs.json.JsString
    import play.api.libs.json.Writes
    import play.api.libs.functional.syntax._
    import com.hbc.api.neighbors.v0.models.json._

    private[v0] implicit val jsonReadsUUID = __.read[String].map(java.util.UUID.fromString)

    private[v0] implicit val jsonWritesUUID = new Writes[java.util.UUID] {
      def writes(x: java.util.UUID) = JsString(x.toString)
    }

    private[v0] implicit val jsonReadsJodaDateTime = __.read[String].map { str =>
      import org.joda.time.format.ISODateTimeFormat.dateTimeParser
      dateTimeParser.parseDateTime(str)
    }

    private[v0] implicit val jsonWritesJodaDateTime = new Writes[org.joda.time.DateTime] {
      def writes(x: org.joda.time.DateTime) = {
        import org.joda.time.format.ISODateTimeFormat.dateTime
        val str = dateTime.print(x)
        JsString(str)
      }
    }

    private[v0] implicit val jsonReadsJodaLocalDate = __.read[String].map { str =>
      import org.joda.time.format.ISODateTimeFormat.dateParser
      dateParser.parseLocalDate(str)
    }

    private[v0] implicit val jsonWritesJodaLocalDate = new Writes[org.joda.time.LocalDate] {
      def writes(x: org.joda.time.LocalDate) = {
        import org.joda.time.format.ISODateTimeFormat.date
        val str = date.print(x)
        JsString(str)
      }
    }

    implicit def jsonReadsApiNeighborsLongAnnKey: play.api.libs.json.Reads[LongAnnKey] = {
      (__ \ "id").read[Long].map { x => new LongAnnKey(id = x) }
    }

    def jsObjectLongAnnKey(obj: com.hbc.api.neighbors.v0.models.LongAnnKey): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsNumber(obj.id)
      )
    }

    implicit def jsonReadsApiNeighborsStringAnnKey: play.api.libs.json.Reads[StringAnnKey] = {
      (__ \ "id").read[String].map { x => new StringAnnKey(id = x) }
    }

    def jsObjectStringAnnKey(obj: com.hbc.api.neighbors.v0.models.StringAnnKey): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id)
      )
    }

    implicit def jsonReadsApiNeighborsUuidAnnKey: play.api.libs.json.Reads[UuidAnnKey] = {
      (__ \ "id").read[_root_.java.util.UUID].map { x => new UuidAnnKey(id = x) }
    }

    def jsObjectUuidAnnKey(obj: com.hbc.api.neighbors.v0.models.UuidAnnKey): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "id" -> play.api.libs.json.JsString(obj.id.toString)
      )
    }

    implicit def jsonReadsApiNeighborsAnn: play.api.libs.json.Reads[Ann] = {
      for {
        id <- (__ \ "id").read[com.hbc.api.neighbors.v0.models.AnnKey]
        score <- (__ \ "score").read[BigDecimal]
      } yield Ann(id, score)
    }

    def jsObjectAnn(obj: com.hbc.api.neighbors.v0.models.Ann): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "id" -> jsObjectAnnKey(obj.id),
        "score" -> play.api.libs.json.JsNumber(obj.score)
      )
    }

    implicit def jsonWritesApiNeighborsAnn: play.api.libs.json.Writes[Ann] = {
      new play.api.libs.json.Writes[com.hbc.api.neighbors.v0.models.Ann] {
        def writes(obj: com.hbc.api.neighbors.v0.models.Ann) = {
          jsObjectAnn(obj)
        }
      }
    }

    implicit def jsonReadsApiNeighborsAnnSearch: play.api.libs.json.Reads[AnnSearch] = {
      for {
        k <- (__ \ "k").read[Int]
        ids <- (__ \ "ids").readNullable[Seq[com.hbc.api.neighbors.v0.models.AnnKey]]
        vectors <- (__ \ "vectors").readNullable[Seq[Seq[BigDecimal]]]
        searchK <- (__ \ "search_k").read[Int]
      } yield AnnSearch(k, ids, vectors, searchK)
    }

    def jsObjectAnnSearch(obj: com.hbc.api.neighbors.v0.models.AnnSearch): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "k" -> play.api.libs.json.JsNumber(obj.k),
        "search_k" -> play.api.libs.json.JsNumber(obj.searchK)
      ) ++ (obj.ids match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("ids" -> play.api.libs.json.Json.toJson(x))
      }) ++
        (obj.vectors match {
          case None => play.api.libs.json.Json.obj()
          case Some(x) => play.api.libs.json.Json.obj("vectors" -> play.api.libs.json.Json.toJson(x))
        })
    }

    implicit def jsonWritesApiNeighborsAnnSearch: play.api.libs.json.Writes[AnnSearch] = {
      new play.api.libs.json.Writes[com.hbc.api.neighbors.v0.models.AnnSearch] {
        def writes(obj: com.hbc.api.neighbors.v0.models.AnnSearch) = {
          jsObjectAnnSearch(obj)
        }
      }
    }

    implicit def jsonReadsApiNeighborsAnnSearchResult: play.api.libs.json.Reads[AnnSearchResult] = {
      for {
        id <- (__ \ "id").readNullable[com.hbc.api.neighbors.v0.models.AnnKey]
        vector <- (__ \ "vector").read[Seq[BigDecimal]]
        neighbors <- (__ \ "neighbors").read[Seq[com.hbc.api.neighbors.v0.models.Ann]]
      } yield AnnSearchResult(id, vector, neighbors)
    }

    def jsObjectAnnSearchResult(obj: com.hbc.api.neighbors.v0.models.AnnSearchResult): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "vector" -> play.api.libs.json.Json.toJson(obj.vector),
        "neighbors" -> play.api.libs.json.Json.toJson(obj.neighbors)
      ) ++ (obj.id match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("id" -> jsObjectAnnKey(x))
      })
    }

    implicit def jsonWritesApiNeighborsAnnSearchResult: play.api.libs.json.Writes[AnnSearchResult] = {
      new play.api.libs.json.Writes[com.hbc.api.neighbors.v0.models.AnnSearchResult] {
        def writes(obj: com.hbc.api.neighbors.v0.models.AnnSearchResult) = {
          jsObjectAnnSearchResult(obj)
        }
      }
    }

    implicit def jsonReadsApiNeighborsHealthcheck: play.api.libs.json.Reads[Healthcheck] = {
      (__ \ "status").read[String].map { x => new Healthcheck(status = x) }
    }

    def jsObjectHealthcheck(obj: com.hbc.api.neighbors.v0.models.Healthcheck): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "status" -> play.api.libs.json.JsString(obj.status)
      )
    }

    implicit def jsonWritesApiNeighborsHealthcheck: play.api.libs.json.Writes[Healthcheck] = {
      new play.api.libs.json.Writes[com.hbc.api.neighbors.v0.models.Healthcheck] {
        def writes(obj: com.hbc.api.neighbors.v0.models.Healthcheck) = {
          jsObjectHealthcheck(obj)
        }
      }
    }

    implicit def jsonReadsApiNeighborsVectorSearchResult: play.api.libs.json.Reads[VectorSearchResult] = {
      for {
        id <- (__ \ "id").read[com.hbc.api.neighbors.v0.models.AnnKey]
        vector <- (__ \ "vector").readNullable[Seq[BigDecimal]]
      } yield VectorSearchResult(id, vector)
    }

    def jsObjectVectorSearchResult(obj: com.hbc.api.neighbors.v0.models.VectorSearchResult): play.api.libs.json.JsObject = {
      play.api.libs.json.Json.obj(
        "id" -> jsObjectAnnKey(obj.id)
      ) ++ (obj.vector match {
        case None => play.api.libs.json.Json.obj()
        case Some(x) => play.api.libs.json.Json.obj("vector" -> play.api.libs.json.Json.toJson(x))
      })
    }

    implicit def jsonWritesApiNeighborsVectorSearchResult: play.api.libs.json.Writes[VectorSearchResult] = {
      new play.api.libs.json.Writes[com.hbc.api.neighbors.v0.models.VectorSearchResult] {
        def writes(obj: com.hbc.api.neighbors.v0.models.VectorSearchResult) = {
          jsObjectVectorSearchResult(obj)
        }
      }
    }

    implicit def jsonReadsApiNeighborsAnnKey: play.api.libs.json.Reads[AnnKey] = new play.api.libs.json.Reads[AnnKey] {
      def reads(js: play.api.libs.json.JsValue): play.api.libs.json.JsResult[AnnKey] = {
        (js \ "ann_key_type").asOpt[String].getOrElse { sys.error("Union[AnnKey] requires a discriminator named 'ann_key_type' - this field was not found in the Json Value") } match {
          case "LongAnnKey" => js.validate[com.hbc.api.neighbors.v0.models.LongAnnKey]
          case "StringAnnKey" => js.validate[com.hbc.api.neighbors.v0.models.StringAnnKey]
          case "UuidAnnKey" => js.validate[com.hbc.api.neighbors.v0.models.UuidAnnKey]
          case other => play.api.libs.json.JsSuccess(com.hbc.api.neighbors.v0.models.AnnKeyUndefinedType(other))
        }
      }
    }

    def jsObjectAnnKey(obj: com.hbc.api.neighbors.v0.models.AnnKey): play.api.libs.json.JsObject = {
      obj match {
        case x: com.hbc.api.neighbors.v0.models.LongAnnKey => jsObjectLongAnnKey(x) ++ play.api.libs.json.Json.obj("ann_key_type" -> "LongAnnKey")
        case x: com.hbc.api.neighbors.v0.models.StringAnnKey => jsObjectStringAnnKey(x) ++ play.api.libs.json.Json.obj("ann_key_type" -> "StringAnnKey")
        case x: com.hbc.api.neighbors.v0.models.UuidAnnKey => jsObjectUuidAnnKey(x) ++ play.api.libs.json.Json.obj("ann_key_type" -> "UuidAnnKey")
        case other => {
          sys.error(s"The type[${other.getClass.getName}] has no JSON writer")
        }
      }
    }

    implicit def jsonWritesApiNeighborsAnnKey: play.api.libs.json.Writes[AnnKey] = {
      new play.api.libs.json.Writes[com.hbc.api.neighbors.v0.models.AnnKey] {
        def writes(obj: com.hbc.api.neighbors.v0.models.AnnKey) = {
          jsObjectAnnKey(obj)
        }
      }
    }
  }
}

package com.hbc.api.neighbors.v0 {

  object Bindables {

    import play.api.mvc.{PathBindable, QueryStringBindable}

    // import models directly for backwards compatibility with prior versions of the generator
    import Core._

    object Core {
      implicit def pathBindableDateTimeIso8601(implicit stringBinder: QueryStringBindable[String]): PathBindable[_root_.org.joda.time.DateTime] = ApibuilderPathBindable(ApibuilderTypes.dateTimeIso8601)
      implicit def queryStringBindableDateTimeIso8601(implicit stringBinder: QueryStringBindable[String]): QueryStringBindable[_root_.org.joda.time.DateTime] = ApibuilderQueryStringBindable(ApibuilderTypes.dateTimeIso8601)

      implicit def pathBindableDateIso8601(implicit stringBinder: QueryStringBindable[String]): PathBindable[_root_.org.joda.time.LocalDate] = ApibuilderPathBindable(ApibuilderTypes.dateIso8601)
      implicit def queryStringBindableDateIso8601(implicit stringBinder: QueryStringBindable[String]): QueryStringBindable[_root_.org.joda.time.LocalDate] = ApibuilderQueryStringBindable(ApibuilderTypes.dateIso8601)
    }

    trait ApibuilderTypeConverter[T] {

      def convert(value: String): T

      def convert(value: T): String

      def example: T

      def validValues: Seq[T] = Nil

      def errorMessage(key: String, value: String, ex: java.lang.Exception): String = {
        val base = s"Invalid value '$value' for parameter '$key'. "
        validValues.toList match {
          case Nil => base + "Ex: " + convert(example)
          case values => base + ". Valid values are: " + values.mkString("'", "', '", "'")
        }
      }
    }

    object ApibuilderTypes {
      import org.joda.time.{format, DateTime, LocalDate}

      val dateTimeIso8601: ApibuilderTypeConverter[DateTime] = new ApibuilderTypeConverter[DateTime] {
        override def convert(value: String): DateTime = format.ISODateTimeFormat.dateTimeParser.parseDateTime(value)
        override def convert(value: DateTime): String = format.ISODateTimeFormat.dateTime.print(value)
        override def example: DateTime = DateTime.now
      }

      val dateIso8601: ApibuilderTypeConverter[LocalDate] = new ApibuilderTypeConverter[LocalDate] {
        override def convert(value: String): LocalDate = format.ISODateTimeFormat.yearMonthDay.parseLocalDate(value)
        override def convert(value: LocalDate): String = value.toString
        override def example: LocalDate = LocalDate.now
      }

    }

    final case class ApibuilderQueryStringBindable[T](
                                                       converters: ApibuilderTypeConverter[T]
                                                     ) extends QueryStringBindable[T] {

      override def bind(key: String, params: Map[String, Seq[String]]): _root_.scala.Option[_root_.scala.Either[String, T]] = {
        params.getOrElse(key, Nil).headOption.map { v =>
          try {
            Right(
              converters.convert(v)
            )
          } catch {
            case ex: java.lang.Exception => Left(
              converters.errorMessage(key, v, ex)
            )
          }
        }
      }

      override def unbind(key: String, value: T): String = {
        s"$key=${converters.convert(value)}"
      }
    }

    final case class ApibuilderPathBindable[T](
                                                converters: ApibuilderTypeConverter[T]
                                              ) extends PathBindable[T] {

      override def bind(key: String, value: String): _root_.scala.Either[String, T] = {
        try {
          Right(
            converters.convert(value)
          )
        } catch {
          case ex: java.lang.Exception => Left(
            converters.errorMessage(key, value, ex)
          )
        }
      }

      override def unbind(key: String, value: T): String = {
        converters.convert(value)
      }
    }

  }

}


package com.hbc.api.neighbors.v0 {

  object Constants {

    val BaseUrl = "http://api-neighbors.services.hbcml.private.hbc.com"
    val Namespace = "com.hbc.api.neighbors.v0"
    val UserAgent = "apibuilder 0.14.3 app.apibuilder.io/hbc/api-neighbors/0.1.0/play_2_6_client"
    val Version = "0.1.0"
    val VersionMajor = 0

  }

  class Client(
                ws: play.api.libs.ws.WSClient,
                val baseUrl: String = "http://api-neighbors.services.hbcml.private.hbc.com",
                auth: scala.Option[com.hbc.api.neighbors.v0.Authorization] = None,
                defaultHeaders: Seq[(String, String)] = Nil
              ) extends interfaces.Client {
    import com.hbc.api.neighbors.v0.models.json._

    private[this] val logger = play.api.Logger("com.hbc.api.neighbors.v0.Client")

    logger.info(s"Initializing com.hbc.api.neighbors.v0.Client for url $baseUrl")

    def anns: Anns = Anns

    def healthchecks: Healthchecks = Healthchecks

    object Anns extends Anns {
      override def getIndexes(
                               requestHeaders: Seq[(String, String)] = Nil
                             )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[String]] = {
        _executeRequest("GET", s"/ann/indexes", requestHeaders = requestHeaders).map {
          case r if r.status == 200 => _root_.com.hbc.api.neighbors.v0.Client.parseJson("Seq[String]", r, _.validate[Seq[String]])
          case r => throw com.hbc.api.neighbors.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200")
        }
      }

      override def getIndexesAndReload(
                                        version: _root_.scala.Option[_root_.java.util.UUID] = None,
                                        requestHeaders: Seq[(String, String)] = Nil
                                      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[String] = {
        val queryParameters = Seq(
          version.map("version" -> _.toString)
        ).flatten

        _executeRequest("GET", s"/ann/indexes/reload", queryParameters = queryParameters, requestHeaders = requestHeaders).map {
          case r if r.status == 202 => _root_.com.hbc.api.neighbors.v0.Client.parseJson("String", r, _.validate[String])
          case r => throw com.hbc.api.neighbors.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 202")
        }
      }

      override def postIndexesAndVectorsByIndexName(
                                                     indexName: String,
                                                     annKeys: Seq[com.hbc.api.neighbors.v0.models.AnnKey],
                                                     requestHeaders: Seq[(String, String)] = Nil
                                                   )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[com.hbc.api.neighbors.v0.models.VectorSearchResult]] = {
        val payload = play.api.libs.json.Json.toJson(annKeys)

        _executeRequest("POST", s"/ann/indexes/${play.utils.UriEncoding.encodePathSegment(indexName, "UTF-8")}/vectors", body = Some(payload), requestHeaders = requestHeaders).map {
          case r if r.status == 200 => _root_.com.hbc.api.neighbors.v0.Client.parseJson("Seq[com.hbc.api.neighbors.v0.models.VectorSearchResult]", r, _.validate[Seq[com.hbc.api.neighbors.v0.models.VectorSearchResult]])
          case r => throw com.hbc.api.neighbors.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200")
        }
      }

      override def postSearchByIndex(
                                      index: String,
                                      annSearch: com.hbc.api.neighbors.v0.models.AnnSearch,
                                      requestHeaders: Seq[(String, String)] = Nil
                                    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[com.hbc.api.neighbors.v0.models.AnnSearchResult]] = {
        val payload = play.api.libs.json.Json.toJson(annSearch)

        _executeRequest("POST", s"/ann/${play.utils.UriEncoding.encodePathSegment(index, "UTF-8")}/search", body = Some(payload), requestHeaders = requestHeaders).map {
          case r if r.status == 200 => _root_.com.hbc.api.neighbors.v0.Client.parseJson("Seq[com.hbc.api.neighbors.v0.models.AnnSearchResult]", r, _.validate[Seq[com.hbc.api.neighbors.v0.models.AnnSearchResult]])
          case r if r.status == 400 => throw com.hbc.api.neighbors.v0.errors.ValueResponse(r)
          case r if r.status == 500 => throw com.hbc.api.neighbors.v0.errors.ValueResponse(r)
          case r => throw com.hbc.api.neighbors.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200, 400, 500")
        }
      }
    }

    object Healthchecks extends Healthchecks {
      override def getHealthcheck(
                                   requestHeaders: Seq[(String, String)] = Nil
                                 )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.hbc.api.neighbors.v0.models.Healthcheck] = {
        _executeRequest("GET", s"/_internal_/healthcheck", requestHeaders = requestHeaders).map {
          case r if r.status == 200 => _root_.com.hbc.api.neighbors.v0.Client.parseJson("com.hbc.api.neighbors.v0.models.Healthcheck", r, _.validate[com.hbc.api.neighbors.v0.models.Healthcheck])
          case r => throw com.hbc.api.neighbors.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200")
        }
      }

      override def getMetrics(
                               requestHeaders: Seq[(String, String)] = Nil
                             )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[String] = {
        _executeRequest("GET", s"/_internal_/metrics", requestHeaders = requestHeaders).map {
          case r if r.status == 200 => _root_.com.hbc.api.neighbors.v0.Client.parseJson("String", r, _.validate[String])
          case r => throw com.hbc.api.neighbors.v0.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200")
        }
      }
    }

    def _requestHolder(path: String): play.api.libs.ws.WSRequest = {

      val holder = ws.url(baseUrl + path).addHttpHeaders(
        "User-Agent" -> Constants.UserAgent,
        "X-Apidoc-Version" -> Constants.Version,
        "X-Apidoc-Version-Major" -> Constants.VersionMajor.toString
      ).addHttpHeaders(defaultHeaders : _*)
      auth.fold(holder) {
        case Authorization.Basic(username, password) => {
          holder.withAuth(username, password.getOrElse(""), play.api.libs.ws.WSAuthScheme.BASIC)
        }
        case a => sys.error("Invalid authorization scheme[" + a.getClass + "]")
      }
    }

    def _logRequest(method: String, req: play.api.libs.ws.WSRequest)(implicit ec: scala.concurrent.ExecutionContext): play.api.libs.ws.WSRequest = {
      val queryComponents = for {
        (name, values) <- req.queryString
        value <- values
      } yield s"$name=$value"
      val url = s"${req.url}${queryComponents.mkString("?", "&", "")}"
      auth.fold(logger.info(s"curl -X $method $url")) { _ =>
        logger.info(s"curl -X $method -u '[REDACTED]:' $url")
      }
      req
    }

    def _executeRequest(
                         method: String,
                         path: String,
                         queryParameters: Seq[(String, String)] = Nil,
                         requestHeaders: Seq[(String, String)] = Nil,
                         body: Option[play.api.libs.json.JsValue] = None
                       )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[play.api.libs.ws.WSResponse] = {
      method.toUpperCase match {
        case "GET" => {
          _logRequest("GET", _requestHolder(path).addHttpHeaders(requestHeaders:_*).addQueryStringParameters(queryParameters:_*)).get()
        }
        case "POST" => {
          _logRequest("POST", _requestHolder(path).addHttpHeaders(_withJsonContentType(requestHeaders):_*).addQueryStringParameters(queryParameters:_*)).post(body.getOrElse(play.api.libs.json.Json.obj()))
        }
        case "PUT" => {
          _logRequest("PUT", _requestHolder(path).addHttpHeaders(_withJsonContentType(requestHeaders):_*).addQueryStringParameters(queryParameters:_*)).put(body.getOrElse(play.api.libs.json.Json.obj()))
        }
        case "PATCH" => {
          _logRequest("PATCH", _requestHolder(path).addHttpHeaders(requestHeaders:_*).addQueryStringParameters(queryParameters:_*)).patch(body.getOrElse(play.api.libs.json.Json.obj()))
        }
        case "DELETE" => {
          _logRequest("DELETE", _requestHolder(path).addHttpHeaders(requestHeaders:_*).addQueryStringParameters(queryParameters:_*)).delete()
        }
        case "HEAD" => {
          _logRequest("HEAD", _requestHolder(path).addHttpHeaders(requestHeaders:_*).addQueryStringParameters(queryParameters:_*)).head()
        }
        case "OPTIONS" => {
          _logRequest("OPTIONS", _requestHolder(path).addHttpHeaders(requestHeaders:_*).addQueryStringParameters(queryParameters:_*)).options()
        }
        case _ => {
          _logRequest(method, _requestHolder(path).addHttpHeaders(requestHeaders:_*).addQueryStringParameters(queryParameters:_*))
          sys.error("Unsupported method[%s]".format(method))
        }
      }
    }

    /**
      * Adds a Content-Type: application/json header unless the specified requestHeaders
      * already contain a Content-Type header
      */
    def _withJsonContentType(headers: Seq[(String, String)]): Seq[(String, String)] = {
      headers.find { _._1.toUpperCase == "CONTENT-TYPE" } match {
        case None => headers ++ Seq(("Content-Type" -> "application/json; charset=UTF-8"))
        case Some(_) => headers
      }
    }

  }

  object Client {

    def parseJson[T](
                      className: String,
                      r: play.api.libs.ws.WSResponse,
                      f: (play.api.libs.json.JsValue => play.api.libs.json.JsResult[T])
                    ): T = {
      f(play.api.libs.json.Json.parse(r.body)) match {
        case play.api.libs.json.JsSuccess(x, _) => x
        case play.api.libs.json.JsError(errors) => {
          throw com.hbc.api.neighbors.v0.errors.FailedRequest(r.status, s"Invalid json for class[" + className + "]: " + errors.mkString(" "))
        }
      }
    }

  }

  sealed trait Authorization extends _root_.scala.Product with _root_.scala.Serializable
  object Authorization {
    final case class Basic(username: String, password: Option[String] = None) extends Authorization
  }

  package interfaces {

    trait Client {
      def baseUrl: String
      def anns: com.hbc.api.neighbors.v0.Anns
      def healthchecks: com.hbc.api.neighbors.v0.Healthchecks
    }

  }

  trait Anns {
    /**
      * List available indexes
      */
    def getIndexes(
                    requestHeaders: Seq[(String, String)] = Nil
                  )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[String]]

    /**
      * Reload all ANN indexes
      *
      * @param version the guid of the ANN version to fetch
      */
    def getIndexesAndReload(
                             version: _root_.scala.Option[_root_.java.util.UUID] = None,
                             requestHeaders: Seq[(String, String)] = Nil
                           )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[String]

    /**
      * Query ANN index for latent vector for specific id
      *
      * @param indexName the name of the index where to query the id
      */
    def postIndexesAndVectorsByIndexName(
                                          indexName: String,
                                          annKeys: Seq[com.hbc.api.neighbors.v0.models.AnnKey],
                                          requestHeaders: Seq[(String, String)] = Nil
                                        )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[com.hbc.api.neighbors.v0.models.VectorSearchResult]]

    /**
      * Search an index for approximate nearest neighbors
      *
      * @param index Index to search
      */
    def postSearchByIndex(
                           index: String,
                           annSearch: com.hbc.api.neighbors.v0.models.AnnSearch,
                           requestHeaders: Seq[(String, String)] = Nil
                         )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[com.hbc.api.neighbors.v0.models.AnnSearchResult]]
  }

  trait Healthchecks {
    def getHealthcheck(
                        requestHeaders: Seq[(String, String)] = Nil
                      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.hbc.api.neighbors.v0.models.Healthcheck]

    def getMetrics(
                    requestHeaders: Seq[(String, String)] = Nil
                  )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[String]
  }

  package errors {

    import com.hbc.api.neighbors.v0.models.json._

    final case class ValueResponse(
                                    response: play.api.libs.ws.WSResponse,
                                    message: Option[String] = None
                                  ) extends Exception(message.getOrElse(response.status + ": " + response.body)){
      lazy val value = _root_.com.hbc.api.neighbors.v0.Client.parseJson("String", response, _.validate[String])
    }

    final case class FailedRequest(responseCode: Int, message: String, requestUri: Option[_root_.java.net.URI] = None) extends _root_.java.lang.Exception(s"HTTP $responseCode: $message")

  }

}