package com.example.splash

import androidx.lifecycle.ViewModel
import com.example.core.BoardingDestination
import com.example.core.LoginDestination
import com.example.core.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    fun navigateToOnBoardingScreen() {
        navigator.navigate(BoardingDestination.route())
    }
}