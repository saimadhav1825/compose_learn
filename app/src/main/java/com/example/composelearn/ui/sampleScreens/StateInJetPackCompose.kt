package com.example.composelearn.ui.sampleScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composelearn.SampleViewModel

@Preview(showBackground = true)
@Composable
fun StateInJetPackCompose() {
    Column(modifier = Modifier.fillMaxSize()) {
        var count by rememberSaveable {
            mutableIntStateOf(0)
        }
        Text(text = "$count")
        Button(onClick = { count++ }) {
            Text(text = "Increase Count")
        }
    }
}

@Composable
fun MyAppSelectedItem(
    modifier: Modifier,
    item: WellnessTask,
    checked: Boolean,
    onCheckChange: (Boolean) -> Unit,
    onClose: () -> Unit = {}
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = item.label,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.weight(1f)
        )

        Checkbox(checked = checked, onCheckChange)
        IconButton(onClick = onClose) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = "Close")
        }
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun ItemPreview() {
    MyAppSelectedItem(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), item = WellnessTask(0, "", false), checked = false, onCheckChange = {

        }
    ) {

    }
}

@Composable
fun SampleList() {
    val sampleViewModel: SampleViewModel = viewModel()
    /* val list = remember {
         List(100) {
             "$it"
         }.toMutableStateList()
     }*/
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(sampleViewModel.tasks) {
            MyAppSelectedItem(modifier = Modifier, item = it, onCheckChange = { check ->
                sampleViewModel.changeTaskChecked(it, check)
            }, checked = it.checked) {
                sampleViewModel.remove(it)
            }
        }
    }
}

class WellnessTask(
    val id: Int,
    val label: String,
    initialChecked: Boolean = false
) {
    var checked: Boolean by mutableStateOf(initialChecked)
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun SampleListPreview() {
    SampleList()
}
