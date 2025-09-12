package com.example.data.e2e

import org.jetbrains.exposed.sql.Database
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.testcontainers.containers.PostgreSQLContainer

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseE2ETest {

    protected lateinit var container: PostgreSQLContainer<Nothing>
    protected lateinit var db: Database

    @BeforeAll
    fun startContainer() {
        container = PostgreSQLContainer<Nothing>("postgres:16").apply {
            withDatabaseName("testdb")
            withUsername("testuser")
            withPassword("testpass")
            start()
        }

        db = Database.connect(
            url = container.jdbcUrl,
            driver = "org.postgresql.Driver",
            user = container.username,
            password = container.password
        )
    }

    @AfterAll
    fun stopContainer() {
        container.stop()
    }
}
