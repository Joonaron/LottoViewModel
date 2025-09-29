package com.example.lottoviewmodel.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Screen(gameViewModel: GameViewModel = viewModel()) {

    val uiState by gameViewModel.uiState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            NumberGrid(
                selectedNumbers = uiState.selectedNumbers,
                onNumberClick = { gameViewModel.onNumberClicked(it) }
            )
        }

        SelectedNumbersDisplay(
            selectedNumbers = uiState.selectedNumbers,
            onReset = { gameViewModel.resetGame() }
        )

        ResultDisplay(
            canCheck = uiState.canCheck,
            matchingNumbers = uiState.matchingNumbers,
            onCheck = { gameViewModel.onCheck() }
        )
    }
}
@Composable
fun NumberGrid(selectedNumbers: List<Int>, onNumberClick: (Int) -> Unit) {
    val numberRange = (1..40).toList()
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        items(numberRange){number ->
            NumberItem(
                number = number,
                isSelected = selectedNumbers.contains(number),
                onNumberClick = { onNumberClick(number) }
            )
        }
    }
}
@Composable
fun NumberItem(
    number: Int,
    isSelected: Boolean,
    onNumberClick: (Int) -> Unit
){
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(
                color = if (isSelected) Color.Green else Color.LightGray,
                shape = CircleShape
            )
            .clickable { onNumberClick(number) },
        contentAlignment = Alignment.Center
    ){
        Text(text = number.toString())
    }
}

@Composable
fun SelectedNumbersDisplay(selectedNumbers: List<Int>, onReset: () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        selectedNumbers.forEach { number ->
            SelectedNumberItem(number = number)
        }
    }
}
@Composable
fun SelectedNumberItem(
    number: Int
){
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(
                color = Color.Green,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center

    ){
        Text(text = number.toString(), color = Color.White)
    }
}

@Composable
fun ResultDisplay(
    canCheck: Boolean,
    matchingNumbers: Int?,
    onCheck: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (canCheck) {
            Button(onClick = onCheck) {
                Text("Check Results")
            }
        }

        if (matchingNumbers != null) {
            Text(
                text = "You matched $matchingNumbers number${if (matchingNumbers != 1) "s" else ""}!",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Blue
            )
        }
    }
}