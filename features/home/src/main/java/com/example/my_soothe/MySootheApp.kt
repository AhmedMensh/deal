package com.example.my_soothe

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MySootheApp() {
    SearchBar(modifier = Modifier)
}


@Composable
fun SearchBar(modifier: Modifier) {
    var searchKeyword by remember { mutableStateOf("") }
    TextField(
        value = searchKeyword,
        onValueChange = {
            searchKeyword = it
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
    )
}