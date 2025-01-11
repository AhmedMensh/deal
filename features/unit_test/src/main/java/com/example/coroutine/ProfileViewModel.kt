package com.example.coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUIState>(ProfileUIState.Idle)
    val uiState = _uiState.asStateFlow()



    private val _uiStateLiveData = MutableLiveData<ProfileUIState>()
    val uiStateLiveData : LiveData<ProfileUIState> = _uiStateLiveData

    init {
        getUserProfile()
    }

    fun getUserProfile() {
        viewModelScope.launch {

            kotlin.runCatching {
                profileUseCase.getProfileDataAsync()
            }.onSuccess {
                _uiState.value = ProfileUIState.Success(it)
                _uiStateLiveData.value =ProfileUIState.Success(it)
            }.onFailure {
                _uiState.value = ProfileUIState.Error(it.message.orEmpty())
                _uiStateLiveData.value = ProfileUIState.Error(it.message.orEmpty())

            }
        }
    }
}

sealed class ProfileUIState {
    data object Idle : ProfileUIState()
    data object Loading : ProfileUIState()
    data class Success(val profile: Profile) : ProfileUIState()
    data class Error(val message: String) : ProfileUIState()
}