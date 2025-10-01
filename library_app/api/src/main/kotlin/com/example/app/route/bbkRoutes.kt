package com.example.app.route

import com.example.app.util.getParam
import com.example.domain.model.BbkModel
import com.example.domain.usecase.bbk.CreateBbkUseCase
import com.example.domain.usecase.bbk.DeleteBbkUseCase
import com.example.domain.usecase.bbk.ReadBbkByCodeUseCase
import com.example.domain.usecase.bbk.ReadBbkByIdUseCase
import com.example.domain.usecase.bbk.UpdateBbkUseCase
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

fun Route.bbkRoutes() {
    val readBbkByIdUseCase by inject<ReadBbkByIdUseCase>()
    val createBbkUseCase by inject<CreateBbkUseCase>()
    val updateBbkUseCase by inject<UpdateBbkUseCase>()
    val deleteBbkUseCase by inject<DeleteBbkUseCase>()
    val readBbkByCodeUseCase by inject<ReadBbkByCodeUseCase>()

    route("/bbk") {
        post {
            val bbk = call.receive<BbkModel>()
            val createdId = createBbkUseCase(bbk)
            call.respond(HttpStatusCode.Created, createdId)
        }

        patch {
            val bbk = call.receive<BbkModel>()
            updateBbkUseCase(bbk)
            call.respond(HttpStatusCode.NoContent)
        }

        get {
            val q = call.request.queryParameters["q"] ?: ""
            val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 0
            val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 20

            val result = readBbkByCodeUseCase(q, page, size)
            call.respond(result)
        }

        route("/{id}") {
            get {
                val bbkId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!

                val author = readBbkByIdUseCase(bbkId)
                if (author == null) {
                    call.respond(HttpStatusCode.NotFound, "Bbk not found")
                } else {
                    call.respond(author)
                }
            }
            delete {
                val bbkId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!
                deleteBbkUseCase(bbkId)
                call.respond(HttpStatusCode.NoContent)
            }
        }

        route("/by-code") {
            get {
                val code = call.getParam<String>("code", true) { it }!!
                val bbk = readBbkByCodeUseCase(code)
                if (bbk == null) {
                    call.respond(HttpStatusCode.NotFound, "Bbk not found")
                } else {
                    call.respond(bbk)
                }
            }
        }

    }
}