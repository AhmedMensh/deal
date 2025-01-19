package com.example.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.my_soothe.MySootheApp

@Composable
fun HomeRoute() {
    HomeScreen()
}

@Composable
fun HomeScreen() {
    MySootheApp()
}
@Immutable
@Stable
data class Contact(val names: List<String>)

@Composable
fun ContactDetails(contact: Contact) {
    Text(text = contact.names.toString())
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun RowScope.MyItem() {
    var count by remember { mutableStateOf(0) }
    println("Item redrawn")
    Text(
        text = "Counter $count",
        fontSize = TextUnit(20f, TextUnitType.Sp),
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterVertically)
            .clickable {
                println("Counter Clicked")
                count++
            })
}