package com.example.recipestorage.objects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.recipestorage.ui.theme.Everglade
import com.example.recipestorage.ui.theme.Typography

var coinStart = 0.0
var incomeStart = 0.0
var incomePriceStart = 10.0
var multiplierStart = 1.0
var multiplierPriceStart = 10.0

val coinState = mutableStateOf(coinStart)

val incomeState = mutableStateOf(incomeStart)
val incomeBought = mutableStateOf(0)
val incomePrice = mutableStateOf(incomePriceStart)

val multiplierState = mutableStateOf(multiplierStart)
val multiplierBought = mutableStateOf(0)
val multiplierPrice = mutableStateOf(multiplierPriceStart)

fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

@Composable
fun PlusCoinClicker(){
    val coins by coinState
    val income by incomeState
    val click by multiplierState

    Column(
        modifier = Modifier
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "${coins.format(1)} Coins",
            modifier = Modifier.padding(20.dp),
            style = Typography.titleLarge,
        )
        Text(
            text = "${income.format(1)} Coins / sec",
            modifier = Modifier.padding(10.dp),
            style = Typography.labelSmall,
        )
        Button(
            onClick = {
                startTimer() //here to start the timer first time a click on the big button happens
                coinState.value += click
            },
            modifier = Modifier
                .size(300.dp),
            colors = ButtonDefaults.buttonColors(Everglade)
        ) {
            Text(
                text = "+${click.format(1)}",
                style = Typography.bodyLarge,
            )
        }
    }
}

@Composable
fun BuyClickPlus(){
    val coins by coinState
    val costs by multiplierPrice

    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Button(
            onClick = {
                if (coins >= costs){
                    coinState.value -= costs
                    multiplierState.value += 1.2
                    multiplierBought.value++
                    multiplierPrice.value *= 1.65
                }
            },
            modifier = Modifier
                .width(220.dp)
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(Everglade)
        ) {
            Text(
                text = "Buy Click Upgrade -${multiplierPrice.value.format(1)}",
                style = Typography.labelSmall,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = "${multiplierBought.value}X",
            style = Typography.bodyLarge,
        )
    }
}

@Composable
fun BuyGenerationPlus(){
    val coins by coinState
    val costs by incomePrice

    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Button(
            onClick = {
                if (coins >= costs){
                    coinState.value -= costs
                    incomeState.value += 0.18
                    incomeBought.value++
                    incomePrice.value *= 1.4
                }
            },
            modifier = Modifier
                .width(220.dp)
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(Everglade)
        ) {
            Text(
                text = "Buy Income Upgrade -${incomePrice.value.format(1)}",
                style = Typography.labelSmall,
                fontSize = Typography.labelSmall.fontSize,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = "${incomeBought.value}X",
            style = Typography.bodyLarge,
        )
    }
}

@Composable
fun ResetClicker(){
    Box(
        modifier = Modifier
    ){
        Button(
            onClick = {
                coinState.value = coinStart
                multiplierState.value = multiplierStart
                multiplierBought.value = 0
                multiplierPrice.value = multiplierPriceStart
                incomeState.value = incomeStart
                incomeBought.value = 0
                incomePrice. value = incomePriceStart

                isRunning.value = false
                elapsedTime.value = 0L
            },
            //modifier = Modifier.size(40.dp),
            colors = ButtonDefaults.buttonColors(Everglade)
        ){
            Text(
                text = "Reset",
                style = Typography.labelSmall,
            )
        }
    }
}