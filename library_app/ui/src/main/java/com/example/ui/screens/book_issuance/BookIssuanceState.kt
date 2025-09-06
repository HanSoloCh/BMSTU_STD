package com.example.ui.screens.book_issuance

import com.example.ui.model.IssuanceModel


sealed class BookIssuanceState {
    object Loading : BookIssuanceState()
    data class Success(val issuanceList: List<IssuanceModel>) : BookIssuanceState()
    data class Error(val message: String) : BookIssuanceState()
}