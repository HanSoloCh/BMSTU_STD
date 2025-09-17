package com.example.data.integration

import com.example.data.local.entity.*
import com.typesafe.config.ConfigFactory
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.testcontainers.containers.PostgreSQLContainer

abstract class BasePostgresIntegrationTest {

    companion object {
        private val config = ConfigFactory.load("application-test.conf")
        private val mode = config.getString("test.database.mode")

        private val container: PostgreSQLContainer<*> by lazy {
            PostgreSQLContainer("postgres:16").apply {
                withDatabaseName("testdb")
                withUsername("testuser")
                withPassword("testpass")
                start()
            }
        }

        val db: Database by lazy {
            when (mode) {
                "external" -> Database.connect(
                    url = config.getString("test.database.url"),
                    driver = "org.postgresql.Driver",
                    user = config.getString("test.database.user"),
                    password = config.getString("test.database.password")
                )
                "container" -> Database.connect(
                    url = container.jdbcUrl,
                    driver = container.driverClassName,
                    user = container.username,
                    password = container.password
                )
                else -> error("Unknown db mode: $mode")
            }
        }

        init {
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
    }

    @After
    fun afterTest() {
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
