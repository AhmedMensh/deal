package com.example.deal

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.Navigator
import com.example.core.NavigatorEvent
import com.example.deal.navigation.AppNavHost
import com.example.deal.ui.DealTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            DealTheme {
                val navController = rememberNavController()
                DealNavigationObserver(navigator = navigationManager, navController = navController)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    AppNavHost(navController = navController)
                }
            }
        }
    }
}

@Composable
fun DealNavigationObserver(navigator: Navigator, navController: NavHostController) {
    LaunchedEffect(navController) {

        navigator.destinations.collect {
            when (val event = it) {
                is NavigatorEvent.NavigateUp -> navController.navigateUp()
                is NavigatorEvent.Directions -> navController.navigate(
                    event.destination,
                    event.builder
                )

                is NavigatorEvent.PopBackStack -> navController.popBackStack()
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DealTheme {
    }
}

