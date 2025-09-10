package com.example.domain.usecase.bbk

import com.example.domain.model.BbkModel
import com.example.domain.repository.BbkRepository
import com.example.domain.specification.bbk.BbkCodeSpecification

class ReadBbkByCodeUseCase(
    private val bbkRepository: BbkRepository
) {
    suspend operator fun invoke(bbkCode: String): List<BbkModel> {
        return bbkRepository.query(BbkCodeSpecification(bbkCode))
    }
}
