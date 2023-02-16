package com.example.topics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun TopicsScreen(){

    val list = listOf("Ahmed","Ahmed","Ahmed","Ahmed","Ahmed","Ahmed","Ahmed")

    LazyColumn {

        items(list){
            Text(it)
        }
    }

}