package com.example.wellness

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatefulWaterCounter(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0) }
    StatelessWaterCounter(count = count, onIncrementClick = { count++ })

}

@Composable
fun StatelessWaterCounter(modifier: Modifier = Modifier, count: Int, onIncrementClick: () -> Unit) {
    Column {
        if (count > 0) {
            Text("You have watered ${count} glasses.")
        }
        Row {
            Button(onClick = onIncrementClick, Modifier.padding(top = 8.dp)) {
                Text("Add One")
            }
        }
    }
}
