package com.example.composelearn

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddRemoveElementsWithSelection() {
    val items = remember { mutableStateListOf<Item?>(null) }
    items.add(Item("1",false))
    items.add(Item("1",false))
    items.add(Item("1",false))
    items.add(Item("1",false))
    items.add(Item("1",false))
    items.add(Item("1",false))
    items.add(Item("1",false))
    var newItemText by remember { mutableStateOf("") }

    // Reference to the keyboard controller
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        // Text input for adding new items
        BasicTextField(
            value = newItemText,
            onValueChange = { newItemText = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (newItemText.isNotBlank()) {
                        items.add(Item(newItemText))
                        newItemText = ""
                        keyboardController?.hide()
                    }
                }
            ),
            modifier = Modifier.padding(16.dp)
        )

        // Display the items
        Column {
            LazyColumn {
                itemsIndexed(items){ index, item ->
                    if (item != null) {
                        ItemRow(
                            item = item,
                            onRemove = { items.removeAt(index) },
                            onItemSelected = { selected ->
                                items[index] = item.copy(selected = selected)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemRow(item: Item, onRemove: () -> Unit, onItemSelected: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemSelected(!item.selected) }
            .padding(16.dp)
            .background(if (item.selected) Color.Gray else Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Display the item title with color change based on selection
        Text(
            text = item.title,
            color = if (item.selected) Color.Red else Color.Black,
            modifier = Modifier.weight(1f)
        )

        // Button to remove the item
        Button(
            onClick = onRemove,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(text = "Remove")
        }
    }
}

data class Item(val title: String, val selected: Boolean = false)
