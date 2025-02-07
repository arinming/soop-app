package com.soop.search

sealed interface SearchUiState {
    data object Loading : SearchUiState
    data object Error : SearchUiState
    data object Success : SearchUiState
}