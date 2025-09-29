package com.example.lottoviewmodel.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState(lottoNumbers = generateLottoNumbers()))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun onNumberClicked(number: Int) {
        val currentList = _uiState.value.selectedNumbers
        val updatedList = if (number in currentList) {
            currentList - number
        } else if (currentList.size < 7) {
            currentList + number
        } else {
            currentList
        }

        _uiState.value = _uiState.value.copy(selectedNumbers = updatedList)
        readyToCheck()
    }

    private fun readyToCheck() {
        val currentList = _uiState.value.selectedNumbers
        val ready = currentList.size == 7
        _uiState.value = _uiState.value.copy(canCheck = ready)
    }

    fun onCheck() {
        val selected = _uiState.value.selectedNumbers
        val winning = _uiState.value.lottoNumbers
        val matchCount = selected.intersect(winning).size

        _uiState.value = _uiState.value.copy(
            matchingNumbers = matchCount,
            hasChecked = true
        )
    }

    fun resetGame() {
        _uiState.value = UiState(lottoNumbers = generateLottoNumbers())
    }

    private fun generateLottoNumbers(): Set<Int> {
        return (1..40).shuffled().take(7).toSet()
    }
}