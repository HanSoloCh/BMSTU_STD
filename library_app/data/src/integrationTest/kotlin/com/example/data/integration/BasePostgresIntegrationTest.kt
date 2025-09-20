package com.example.data.integration

import com.example.data.local.entity.*
import com.example.data.local.repository.BookRepositoryImpl
import com.example.domain.model.BookModel
import com.typesafe.config.ConfigFactory
import kotlinx.coroutines.test.runTest
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import org.testcontainers.containers.PostgreSQLContainer
import java.util.UUID

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BasePostgresIntegrationTest {

    // Контейнер и база будут инициализированы в setupDatabase
    protected lateinit var container: PostgreSQLContainer<Nothing>
    protected lateinit var db: Database
    protected lateinit var config: com.typesafe.config.Config
    protected lateinit var mode: String

    @BeforeAll
    fun setupDatabase() {
        config = ConfigFactory.load("application-test.conf")
        mode = config.getString("test.database.mode")

        db = when (mode) {
            "container" -> {
                container = PostgreSQLContainer<Nothing>("postgres:16").apply {
                    withDatabaseName("testdb")
                    withUsername("testuser")
                    withPassword("testpass")
                    start()
                }
                Database.connect(
                    url = container.jdbcUrl,
                    driver = container.driverClassName,
                    user = container.username,
                    password = container.password
                )
            }
            "external" -> {
                Database.connect(
                    url = config.getString("test.database.url"),
                    driver = config.getString("test.database.driver"),
                    user = config.getString("test.database.user"),
                    password = config.getString("test.database.password")
                )
            }
            else -> error("Unknown db mode: $mode")
        }

        // Создаём таблицы один раз
        transaction(db) {
            SchemaUtils.createMissingTablesAndColumns(
                ApuEntity,
                AuthorEntity,
                BbkEntity,
                BookAuthorCrossRef,
                BookEntity,
                IssuanceEntity,
                PublisherEntity,
                ReservationEntity,
                UserEntity,
                UserFavoriteCrossRef
            )
        }
    }

    @AfterAll
    fun teardownDatabase() {
        if (::container.isInitialized && mode == "container") {
            container.stop()
        }
    }

    @BeforeEach
    fun cleanDatabase() {
        transaction(db) {
            ReservationEntity.deleteAll()
            IssuanceEntity.deleteAll()
            UserFavoriteCrossRef.deleteAll()
            UserEntity.deleteAll()
            AuthorEntity.deleteAll()
            BookEntity.deleteAll()
            BookAuthorCrossRef.deleteAll()
            PublisherEntity.deleteAll()
            ApuEntity.deleteAll()
            BbkEntity.deleteAll()
        }
    }
}