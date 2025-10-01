package com.example.app.route

import com.example.app.util.getParam
import com.example.domain.model.AuthorModel
import com.example.domain.usecase.author.CreateAuthorUseCase
import com.example.domain.usecase.author.DeleteAuthorUseCase
import com.example.domain.usecase.author.ReadAuthorByIdUseCase
import com.example.domain.usecase.author.ReadAuthorByNameUseCase
import com.example.domain.usecase.author.UpdateAuthorUseCase
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

fun Route.authorRoutes() {
    val readAuthorByIdUseCase: ReadAuthorByIdUseCase by inject()
    val createAuthorUseCase: CreateAuthorUseCase by inject()
    val updateAuthorUseCase: UpdateAuthorUseCase by inject()
    val deleteAuthorUseCase: DeleteAuthorUseCase by inject()
    val readAuthorByNameUseCase: ReadAuthorByNameUseCase by inject()

    route("/author") {
        post {
            val author = call.receive<AuthorModel>()
            val createdId = createAuthorUseCase(author)
            call.respond(HttpStatusCode.Created, createdId)
        }

        patch {
            val author = call.receive<AuthorModel>()
            updateAuthorUseCase(author)
            call.respond(HttpStatusCode.NoContent)
        }

        get {
            val q = call.request.queryParameters["q"] ?: ""
            val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 0
            val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 20

            val result = readAuthorByNameUseCase(q, page, size)
            call.respond(result)
        }

        route("/{id}") {
            get {
                val authorId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!

                val author = readAuthorByIdUseCase(authorId)
                if (author == null) {
                    call.respond(HttpStatusCode.NotFound, "Author not found")
                } else {
                    call.respond(author)
                }
            }
            delete {
                val authorId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!
                deleteAuthorUseCase(authorId)
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}

