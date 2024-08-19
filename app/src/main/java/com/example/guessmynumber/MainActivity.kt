package com.example.guessmynumber

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guessmynumber.ui.theme.GuessMyNumberTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessMyNumberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                     GuessMyNumber()
                }
            }
        }
    }
}

@Composable
fun GuessMyNumber(){
    var randomNumber = (1..100).random()
    var inputValue by remember {mutableStateOf("")}
    var counter by remember{mutableStateOf(0)}
    val context = LocalContext.current

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        color = Color.Black
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Guess The Number", style = customTextStyle )
        Spacer(modifier = Modifier.height(32.dp))
        Text("Can you guess what i am thinking? It is a number between 1 to 100.", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue ,
            onValueChange = {
                inputValue = it
            },
            label = {Text("Enter value")} )
        Button(onClick = {
            val userGuess = inputValue.toIntOrNull()


            when {
                userGuess == null -> {
                    Toast.makeText(context, "Please enter a valid number", Toast.LENGTH_SHORT).show()
                }
                userGuess == randomNumber -> {
                    Toast.makeText(context, "Congratulations! You guessed the number!", Toast.LENGTH_SHORT).show()
                    randomNumber = (1..100).random()
                    counter = 0
                }
                counter >= 4 -> {
                    Toast.makeText(context, "Sorry, you have no more guesses. The number was $randomNumber. Try again!", Toast.LENGTH_SHORT).show()
                    randomNumber = (1..100).random()
                    counter = 0
                }
                else -> {
                    Toast.makeText(context, "Try again!", Toast.LENGTH_SHORT).show()
                    counter++
                }
            }

        }) {
            Text("Submit Guess")
        }
        Text("Tried : $counter", fontSize = 16.sp )

    }
}




