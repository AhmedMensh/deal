package com.example.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.designsystem.core.component.DealTextButton

@Composable
fun CategoriesRoute() {

    CategoriesScreen()
}

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    Column() {
        DealTextButton(onClick = { /*TODO*/ }, text = {
            Text(text = "Go")
        }) {

        }
        Text("${viewModel.uiState}")

    }
}