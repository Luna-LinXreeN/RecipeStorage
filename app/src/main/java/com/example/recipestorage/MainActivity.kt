package com.example.recipestorage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipestorage.objects.BuyClickPlus
import com.example.recipestorage.objects.BuyGenerationPlus
import com.example.recipestorage.objects.PlusCoinClicker
import com.example.recipestorage.objects.ResetClicker
import com.example.recipestorage.objects.TimerScreen
import com.example.recipestorage.ui.theme.RecipeStorageTheme
import com.example.recipestorage.objects.generateCoins
import com.example.recipestorage.objects.isRunning
import com.example.recipestorage.ui.theme.DeYork
import com.example.recipestorage.ui.theme.FruitSalad
import java.io.StringReader


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            preStartUp()
            Startup()
        }
    }
} //TODO ask for help with sharedPreferences because I have no idea how I would Implement

@Composable
fun Background() {
    Column(
        modifier = Modifier
            .background(DeYork), //TODO scheme colors still dont work MaterialTheme.colorScheme.background
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        ResetClicker()

        TimerScreen()
        PlusCoinClicker()
        BuyClickPlus()
        BuyGenerationPlus()
    }
}

@Preview(showBackground = true)
@Composable
fun Startup() {
    RecipeStorageTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(FruitSalad)
        ) {
            Background()
        }
    }
}

fun preStartUp(){
    generateCoins()
}
