package com.example.domain.usecase.issuance

import com.example.domain.exception.InvalidValueException
import com.example.domain.model.IssuanceModel
import com.example.domain.repository.IssuanceRepository
import com.example.domain.specification.AndSpecification
import com.example.domain.specification.issuance.IssuanceBookIdSpecification
import com.example.domain.specification.issuance.IssuanceUserIdSpecification
import java.util.UUID

class ReadIssuanceUseCase(
    private val issuanceRepository: IssuanceRepository
) {
    suspend operator fun invoke(bookId: UUID?, userId: UUID?): List<IssuanceModel> {
        if (bookId != null && userId != null)
            return issuanceRepository.query(
                AndSpecification(
                    listOf(
                        IssuanceBookIdSpecification(bookId),
                        IssuanceUserIdSpecification(userId)
                    )
                )
            )
        else if (bookId != null)
            return issuanceRepository.query(
                IssuanceBookIdSpecification(bookId),
            )
        else if (userId != null)
            return issuanceRepository.query(IssuanceUserIdSpecification(userId))
        else
            throw InvalidValueException("bookId, userId", "null, null")
    }
}