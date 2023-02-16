package com.example.categories

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val categoriesRoute = "categories"

fun NavController.navigateToCategoriesScreen(navOptions: NavOptions ? = null){
    this.navigate(categoriesRoute,navOptions)
}

fun NavGraphBuilder.categoriesScreen(){

    composable(route = categoriesRoute){
        CategoriesRoute()
    }
}