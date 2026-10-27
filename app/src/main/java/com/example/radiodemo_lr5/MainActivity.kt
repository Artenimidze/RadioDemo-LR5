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

fun handle_change(sum: Int?, n: Int?, tip: Float, discount: Float): Float? {
    return if (sum != null && n != null) {
        ((sum * n).toFloat() + ((sum * n).toFloat() * (tip/100))) * (1f - discount)
    } else {
        null
    }
}
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf<Float?>(null) }
    var discount by remember {mutableFloatStateOf(0.03f)}
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
                    result = handle_change(sum.toIntOrNull(), n.toIntOrNull(), tip, discount)
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
                    result = handle_change(sum.toIntOrNull(), n.toIntOrNull(), tip, discount)
                    var r = n.toIntOrNull()
                    if (r == null) { r = 1}
                    if (r < 3) {
                        radioSelected = 1
                        discount = 0.03f
                    } else if (r < 6) {
                        radioSelected = 2
                        discount = 0.05f
                    } else if (r < 11) {
                        radioSelected = 3
                        discount = 0.07f
                    } else {
                        radioSelected = 4
                        discount = 0.1f
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
                    result = handle_change(sum.toIntOrNull(), n.toIntOrNull(), tip, discount)
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
        result?.let { r ->
            Text ("Итоговая сумма: ${"%.2f".format(r)}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RadioPreview () {
    MainScreen()
}