package com.example.boarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.designsystem.core.component.DealFilledButton
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import com.example.designsystem.R as systemResources

@Composable
fun BoardingRout() {
    BoardingScreen()
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BoardingScreen(
    viewModel: BoardingViewModel = hiltViewModel()
) {

    val slideImage = remember { mutableStateOf(systemResources.drawable.ic_onboarding1) }
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        HorizontalPager(count = 3, state = pagerState) { page ->

            when (page) {
                0 -> slideImage.value = systemResources.drawable.ic_onboarding1
                1 -> slideImage.value = systemResources.drawable.ic_onboarding2
                2 -> slideImage.value = systemResources.drawable.ic_onboarding3
            }


            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = slideImage.value), contentDescription = "")
                Text(
                    text = stringResource(id = systemResources.string.place_holder),
                    modifier = Modifier.padding(20.dp),
                    textAlign = TextAlign.Center
                )

            }

        }

        Spacer(modifier = Modifier.height(20.dp))
        DotsIndicator(
            totalDots = 3,
            selectedIndex = pagerState.currentPage,
            selectedColor = Color.Green,
            unSelectedColor = Color.Green.copy(alpha = .2f)
        )

        Spacer(modifier = Modifier.height(50.dp))

        if(pagerState.currentPage == 2)
            DealFilledButton(colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green.copy(alpha = .5f)
            ), onClick = {
                viewModel.navigateToLoginScreen()
            }) {
                Text(text = "Get Started", style = TextStyle(color = Color.White))
            }
        else
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            DealFilledButton(colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green.copy(alpha = .5f)
            ), onClick = {
                coroutineScope.launch {
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                }
            }) {
                Text(text = "Next", style = TextStyle(color = Color.White))
            }

            DealFilledButton(colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green.copy(alpha = .5f)
            ), onClick = {
                if (pagerState.currentPage > 0)
                    coroutineScope.launch {
                        pagerState.scrollToPage(pagerState.currentPage - 1)
                    }
            }) {
                Text(text = "Previous", style = TextStyle(color = Color.White))
            }
        }
    }

}

@Composable
fun DotsIndicator(
    totalDots: Int, selectedIndex: Int, selectedColor: Color, unSelectedColor: Color
) {

    LazyRow {
        items(totalDots) { index ->

            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .width(20.dp)
                        .height(8.dp)
                        .clip(CircleShape)
                        .background(selectedColor)

                )
            } else Box(
                modifier = Modifier
                    .padding(2.dp)
                    .width(15.dp)
                    .height(8.dp)
                    .clip(CircleShape)
                    .background(unSelectedColor)

            )
        }
    }
}