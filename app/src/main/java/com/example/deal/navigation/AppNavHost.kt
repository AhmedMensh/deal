package com.example.deal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.boarding.BoardingRout
import com.example.core.*
import com.example.home.HomeRoute
import com.example.login.LoginRoute
import com.example.register.RegisterRoute
import com.example.splash.SplashRoute

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = HomeDestination.route()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        composable(SplashDestination.route()) {
            SplashRoute()
        }
        composable(BoardingDestination.route()) {
            BoardingRout()
        }
        composable(LoginDestination.route()) {
            LoginRoute()
        }
        composable(RegisterDestination.route()) {
            RegisterRoute()
        }

        composable(HomeDestination.route()) {
            HomeRoute()
        }


    }
}
