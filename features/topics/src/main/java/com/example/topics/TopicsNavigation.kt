package com.example.topics

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val topicRoute = "topics"

fun NavController.navigateToTopicsScreen(navOptions: NavOptions ? = null){
    this.navigate(topicRoute,navOptions)
}

fun NavGraphBuilder.topicScreen(){

    composable(route = topicRoute){

    }
}
