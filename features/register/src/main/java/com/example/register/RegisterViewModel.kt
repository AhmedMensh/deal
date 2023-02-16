package com.example.register

import androidx.lifecycle.ViewModel
import com.example.core.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val navigator: Navigator
): ViewModel() {


    fun navigateBack(){
        navigator.navigateUp()
    }
}