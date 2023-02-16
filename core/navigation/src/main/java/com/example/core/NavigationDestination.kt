package com.example.core

import androidx.navigation.NamedNavArgument

interface NavigationDestination {

    fun route(): String
    val arguments: List<NamedNavArgument>
        get() = emptyList()
}