package com.example.radiodemo_lr5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.radiodemo_lr5.ui.theme.RadioDemoLR5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RadioDemoLR5Theme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var radioSelected by remember { mutableIntStateOf(1) }
    var sum by remember { mutableStateOf("") }
    var n by remember { mutableStateOf("") }
    var tip by remember { mutableFloatStateOf(0f) }
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Сумма заказа")
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = sum,
                onValueChange = {
                    sum = it
                },
                singleLine = true
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Количество блюд")
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = n,
                onValueChange = {
                    n = it
                    var r = n.toIntOrNull()
                    if (r == null) { r = 1}
                    radioSelected = if (r < 3) {
                        1
                    } else if (r < 6) {
                        2
                    } else if (r < 11) {
                        3
                    } else {
                        4
                    }
                },
                singleLine = true
            )
        }
        Column(

        ) {
            Text("Чаевые")
            Slider(
                modifier = modifier.fillMaxWidth(),
                value = tip,
                onValueChange = {
                    tip = it
                },
                valueRange = 0f..25f,
                steps = 25
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("0%")
                Text("25%")
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text("Скидка")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(

                ) {
                    RadioButton(
                        selected = (radioSelected == 1),
                        onClick = null
                    )
                    Text("3%")
                }
                Column(

                ) {
                    RadioButton(
                        selected = (radioSelected == 2),
                        onClick = null
                    )
                    Text("5%")

                }
                Column(

                ) {
                    RadioButton(
                        selected = (radioSelected == 3),
                        onClick = null
                    )
                    Text("7%")
                }
                Column(

                ) {
                    RadioButton(
                        selected = (radioSelected == 4),
                        onClick = null
                    )
                    Text("10%")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RadioPreview () {
    MainScreen()
}