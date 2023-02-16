package com.example.core

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

interface Navigator {

    fun navigateUp(): Boolean
    fun popBackStack()
    fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }): Boolean
    val destinations: Flow<NavigatorEvent>
}