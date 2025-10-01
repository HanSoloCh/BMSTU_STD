package com.example.app.route

import com.example.app.util.getParam
import com.example.domain.model.UserModel
import com.example.domain.usecase.user.CreateUserUseCase
import com.example.domain.usecase.user.DeleteUserUseCase
import com.example.domain.usecase.user.ReadUserByIdUseCase
import com.example.domain.usecase.user.ReadUserByPhoneUseCase
import com.example.domain.usecase.user.UpdateUserUseCase
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

fun Route.userRoutes() {
    val readUserByIdUseCase by inject<ReadUserByIdUseCase>()
    val createUserUseCase by inject<CreateUserUseCase>()
    val updateUserUseCase by inject<UpdateUserUseCase>()
    val deleteUserUseCase by inject<DeleteUserUseCase>()
    val readUserByPhoneUseCase by inject<ReadUserByPhoneUseCase>()

    route("/user") {
        post {
            val user = call.receive<UserModel>()
            val createdId = createUserUseCase(user)
            call.respond(HttpStatusCode.Created, createdId)
        }

        patch {
            val user = call.receive<UserModel>()
            updateUserUseCase(user)
            call.respond(HttpStatusCode.NoContent)
        }

        get {
            val q = call.request.queryParameters["q"] ?: ""
            val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 0
            val size = call.request.queryParameters["size"]?.toIntOrNull() ?: 20

            val result = readUserByPhoneUseCase(q, page, size)
            call.respond(result)
        }

        route("/{id}") {
            get {
                val userId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!

                val user = readUserByIdUseCase(userId)
                if (user == null) {
                    call.respond(HttpStatusCode.NotFound, "User not found")
                } else {
                    call.respond(user)
                }
            }
            delete {
                val userId = call.getParam<UUID>("id", true) { UUID.fromString(it) }!!
                deleteUserUseCase(userId)
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}