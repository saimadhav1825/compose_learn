package com.example.composelearn.ui.sampleScreens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Composable
fun FirstScreen(
    list: List<String> = List(1000) {
        "$it"
    },
    onNavigate: () -> Unit = {},
) {

    Column {

        LazyColumn(state = rememberLazyListState()) {
            items(list) {
                var expanded by remember { mutableStateOf(false) }

                val extraPadding by animateDpAsState(
                    if (expanded) 48.dp else 0.dp, label = ""
                )
                Surface(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp).clickable {
                        onNavigate.invoke()
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(bottom = extraPadding)
                        ) {
                            Text(text = it)
                        }

                        Button(onClick = {
                            expanded = !expanded
                        }) {
                            Text(if (expanded) "Show less" else "Show more")
                        }
                    }
                }
            }
        }
    }

}
@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun FirstScreenView() {
    FirstScreen {

    }
}