package com.example.lottoviewmodel.ui

data class UiState(
    val selectedNumbers: List<Int> = emptyList(),
    val lottoNumbers: Set<Int> = emptySet(),
    val matchingNumbers: Int? = null,
    val canCheck: Boolean = false,
    val hasChecked: Boolean = false
)