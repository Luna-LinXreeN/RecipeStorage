package com.example.recipestorage.objects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.util.*

val isRunning = mutableStateOf(false)
val elapsedTime = mutableStateOf(0L)

@Preview(showBackground = true)
@Composable
fun TimerScreen() {
    LaunchedEffect(isRunning.value) {
        while (isRunning.value) {
            delay(1000) // Update the timer every second
            if (isRunning.value){
                elapsedTime.value += 1000
            }
        }
    }

    val formattedTime = remember(elapsedTime.value) {
        val totalSeconds = elapsedTime.value / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    Column(
        modifier = Modifier
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formattedTime,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

fun generateCoins(){
    val income by incomeState
    if (income > 0){
        Thread.sleep(1000)
        coinState.value += income
    }
}

fun startTimer(){
    if(!isRunning.value){
        isRunning.value = true
    }
}