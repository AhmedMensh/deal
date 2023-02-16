package com.example.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.HomeDestination
import com.example.core.Navigator
import com.example.core.RegisterDestination
import com.example.domain.RequestLoginUseCase
import com.example.models.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val requestLoginUseCase: RequestLoginUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState.Ideal)
    val loginState: StateFlow<LoginUiState> = _loginState.asStateFlow()
    fun navigateToRegisterScreen() {
        navigator.navigate(RegisterDestination.route())
    }
    fun navigateToHomeScreen() {
        navigator.navigate(HomeDestination.route())
    }

    fun requestLogin(userName: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginUiState.Loading
            requestLoginUseCase(userName, password).collect {
                when (it) {
                    is DataResult.Success -> {
                        _loginState.emit(LoginUiState.Success)
                    }
                    is DataResult.Error -> {
                        _loginState.emit(LoginUiState.Error(it.error))

                    }

                }

            }
        }
    }
}

sealed interface LoginUiState {
    object Ideal : LoginUiState
    object Loading : LoginUiState
    object Success : LoginUiState
    data class Error(val message: String) : LoginUiState
}