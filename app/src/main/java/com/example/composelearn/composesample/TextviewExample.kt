package com.example.composelearn.composesample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@Composable
fun TextviewExample() {
    Column {
        // Creating a variable to store the TextField value
        var value by remember { mutableStateOf(TextFieldValue("")) }
        BasicTextField(
            value = value,
            onValueChange = { value = it },
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(Color.LightGray, RoundedCornerShape(percent = 30))
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {

                    if (value.text.isEmpty()) {
                        Text("Enter Something...")
                    }
                    // <-- Add this
                    innerTextField()
                }
            },
        )
        println("Parent")
        Text("Parent", style = TextStyle(color = MaterialTheme.colorScheme.error))
        ChildText{
            value.text
        }

    }


}

@Composable
fun ChildText(text: ()->String) {
    println("Child")
    Text(text = text.invoke(), style = TextStyle(color = MaterialTheme.colorScheme.error))
}
