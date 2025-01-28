package com.example.wellness

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    viewModel: WellnessViewModel = WellnessViewModel()
) {


    LazyColumn {
        items(
            viewModel.tasks,
            key = { task -> task.id }) { task ->
            WellnessTaskItem(modifier, task, { task, checked ->
                viewModel.changeTaskChecked(task, checked)
            }, {
                viewModel.remove(task)
            })
        }
    }

}


@Composable
fun WellnessTaskItem(
    modifier: Modifier = Modifier,
    task: WellnessTask,
    onCheckedChange: (WellnessTask, Boolean) -> Unit,
    onClose: () -> Unit
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = task.checked, onCheckedChange = { onCheckedChange.invoke(task, it) })
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = task.name
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

data class WellnessTask(
    val id: Int,
    val name: String,
    val initialChecked: Boolean = false
){
    var checked by mutableStateOf(initialChecked)
}