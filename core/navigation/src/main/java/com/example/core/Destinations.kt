package com.example.core

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object SplashDestination : NavigationDestination{
    override fun route() = "splash"
}



object BoardingDestination : NavigationDestination{
    override fun route() = "boarding"
}
object RegisterDestination : NavigationDestination{
    override fun route() = "register"
}

object HomeDestination : NavigationDestination{
    override fun route() = "home"
}


object LoginDestination : NavigationDestination{
    override fun route() = "login"

    override val arguments: List<NamedNavArgument>
        get() = listOf(navArgument(USER_ID){ type = NavType.IntType})



    private const val USER_ID = "user-id"
}