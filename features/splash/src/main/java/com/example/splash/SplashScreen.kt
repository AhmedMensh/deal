package com.example.splash

import android.os.Build
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay

@Composable
fun SplashRoute(
) {
    SplashScreen()
}

@Composable
fun SplashScreen(

) {
    val viewModel: SplashViewModel = hiltViewModel()

    val scale = remember {
        Animatable(0f)
    }

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
//                easing = {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
//                        OvershootInterpolator(4f).getInterpolation(it)
//                    } else {
//                        TODO("VERSION.SDK_INT < DONUT")
//                    }
//                }
            )
        )
        delay(2_000L)
        viewModel.navigateToOnBoardingScreen()
    }

    // Image
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = com.example.designsystem.R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
//                .width(100.dp)
//                .height(100.dp)
        )
    }

}