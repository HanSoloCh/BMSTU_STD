package com.example.domain.integration

import com.example.data.local.entity.BbkEntity
import com.example.data.local.repository.ApuRepositoryImpl
import com.example.data.local.repository.BbkRepositoryImpl
import com.example.domain.model.ApuModel
import com.example.domain.specification.apu.ApuTermSpecification
import com.example.domain.usecase.apu.CreateApuUseCase
import kotlinx.coroutines.test.runTest
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.util.UUID

class CreateApuUseCaseIntegrationTest : BasePostgresIntegrationTest() {

    private lateinit var repository: ApuRepositoryImpl
    private lateinit var useCase: CreateApuUseCase
    private lateinit var bbkId: UUID

    @Before
    fun setup() {
        repository = ApuRepositoryImpl(db)
        useCase = CreateApuUseCase(repository)

        transaction(db) {
            bbkId = BbkEntity.insertAndGetId {
                it[code] = "Test Code"
                it[description] = "Test Description"
            }.value
        }
    }

    @Test
    fun `create APU via use case`() = runTest {
        val apu = ApuModel(UUID.randomUUID(), "Integration Test", bbkId)

        val id = useCase.execute(apu)

        val found = repository.readById(id)
        assertNotNull(found)
        assertEquals(apu, found)
    }
}
