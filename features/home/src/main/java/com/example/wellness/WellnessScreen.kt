package com.example.wellness

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    viewModel: WellnessViewModel = WellnessViewModel()
) {

    Column {
        StatefulWaterCounter(modifier)
        WellnessTasksList(modifier)
    }
}