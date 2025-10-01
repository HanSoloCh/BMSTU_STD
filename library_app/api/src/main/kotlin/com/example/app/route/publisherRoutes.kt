package com.example.app.route

import com.example.app.util.getParam
import com.example.domain.model.PublisherModel
import com.example.domain.usecase.publisher.CreatePublisherUseCase
import com.example.domain.usecase.publisher.DeletePublisherUseCase
import com.example.domain.usecase.publisher.ReadPublisherByIdUseCase
import com.example.domain.usecase.publisher.ReadPublisherByNameUseCase
import com.example.domain.usecase.publisher.UpdatePublisherUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.patch
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.publisherRoutes() {
    val readPublisherByIdUseCase by inject<ReadPublisherByIdUseCase>()
    val createPublisherUseCase by inject<CreatePublisherUseCase>()
    val updatePublisherUseCase by inject<UpdatePublisherUseCase>()
    val deletePublisherUseCase by inject<DeletePublisherUseCase>()
    val readPublisherByNameUseCase by inject<ReadPublisherByNameUseCase>()

    route("/publisher") {
        post {
            val publisher = call.receive<PublisherModel>()
            val createdId = createPublisherUseCase(publisher)
            call.respond(HttpStatusCode.Created, createdId)
        }

        patch {
            val publisher = call.receive<PublisherModel>()
            updatePublisherUseCase(publisher)
            call.respond(HttpStatusCode.NoContent)
        }

        get {
            val q = call.request.queryParameters["q"] ?: ""
            val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 0
            val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 20

            val result = readPublisherByNameUseCase(q, page, size)
            call.respond(result)
        }

        route("/{id}") {
            get {
                val publisherId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!

                val author = readPublisherByIdUseCase(publisherId)
                if (author == null) {
                    call.respond(HttpStatusCode.NotFound, "Publisher not found")
                } else {
                    call.respond(author)
                }
            }
            delete {
                val publisherId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!
                deletePublisherUseCase(publisherId)
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}