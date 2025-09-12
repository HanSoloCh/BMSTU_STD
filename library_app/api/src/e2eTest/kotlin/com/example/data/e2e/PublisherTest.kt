package com.example.data.e2e

import com.example.app.module
import com.example.app.serializer.InstantSerializer
import com.example.app.serializer.LocalDateSerializer
import com.example.app.serializer.UUIDSerializer
import com.example.domain.model.PublisherModel
import com.typesafe.config.ConfigFactory
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.config.HoconApplicationConfig
import io.ktor.server.testing.ApplicationTestBuilder
import io.ktor.server.testing.testApplication
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.LocalDate
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class PublisherE2ETest : BaseE2ETest() {

    private val testJson = Json {
        serializersModule = SerializersModule {
            contextual(UUID::class, UUIDSerializer)
            contextual(LocalDate::class, LocalDateSerializer)
            contextual(Instant::class, InstantSerializer)
        }
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    private fun testConfig() = HoconApplicationConfig(
        ConfigFactory.parseMap(
            mapOf(
                "ktor.database.driver" to "org.postgresql.Driver",
                "ktor.database.url" to container.jdbcUrl,
                "ktor.database.user" to container.username,
                "ktor.database.password" to container.password,
                "ktor.database.maxPoolSize" to "5"
            )
        )
    )

    private fun ApplicationTestBuilder.testClient(): HttpClient =
        createClient {
            install(ContentNegotiation) { json(testJson) }
        }

    @Test
    fun `create publisher`() = testApplication {
        environment { config = testConfig() }
        application { module() }

        val client = testClient()
        val publisher = PublisherModel(UUID.randomUUID(), "O’Reilly")

        val response = client.post("/publisher") {
            contentType(ContentType.Application.Json)
            setBody(testJson.encodeToString(PublisherModel.serializer(), publisher))
        }

        assertEquals(HttpStatusCode.Created, response.status)
    }

    @Test
    fun `read publisher by id`() = testApplication {
        environment { config = testConfig() }
        application { module() }

        val client = testClient()
        val publisher = PublisherModel(UUID.randomUUID(), "Packt")

        client.post("/publisher") {
            contentType(ContentType.Application.Json)
            setBody(testJson.encodeToString(PublisherModel.serializer(), publisher))
        }

        val response = client.get("/publisher/${publisher.id}")
        assertEquals(HttpStatusCode.OK, response.status)

        val result = testJson.decodeFromString(PublisherModel.serializer(), response.bodyAsText())
        assertEquals(publisher, result)
    }

    @Test
    fun `update publisher`() = testApplication {
        environment { config = testConfig() }
        application { module() }

        val client = testClient()
        val publisher = PublisherModel(UUID.randomUUID(), "O’Reilly")

        client.post("/publisher") {
            contentType(ContentType.Application.Json)
            setBody(testJson.encodeToString(PublisherModel.serializer(), publisher))
        }

        val updated = publisher.copy(name = "O’Reilly Media")
        val response = client.put("/publisher") {
            contentType(ContentType.Application.Json)
            setBody(testJson.encodeToString(PublisherModel.serializer(), updated))
        }

        assertEquals(HttpStatusCode.NoContent, response.status)

        val check = client.get("/publisher/${publisher.id}")
        assertTrue(check.bodyAsText().contains("O’Reilly Media"))
    }

    @Test
    fun `delete publisher`() = testApplication {
        environment { config = testConfig() }
        application { module() } // <-- добавил

        val client = testClient()
        val publisher = PublisherModel(UUID.randomUUID(), "Manning")

        client.post("/publisher") {
            contentType(ContentType.Application.Json)
            setBody(testJson.encodeToString(PublisherModel.serializer(), publisher))
        }

        val deleteResponse = client.delete("/publisher/${publisher.id}")
        assertEquals(HttpStatusCode.NoContent, deleteResponse.status)

        val afterDelete = client.get("/publisher/${publisher.id}")
        assertEquals(HttpStatusCode.NotFound, afterDelete.status)
    }
}
