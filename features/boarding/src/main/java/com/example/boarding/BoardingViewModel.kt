package com.example.boarding

import androidx.lifecycle.ViewModel
import com.example.core.LoginDestination
import com.example.core.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BoardingViewModel @Inject constructor(
    private val navigator: Navigator
): ViewModel() {

    fun navigateToLoginScreen(){
        navigator.navigate(LoginDestination.route())
    }
}