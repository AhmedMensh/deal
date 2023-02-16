package com.example.categories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.models.CategoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<CategoriesScreenUiState> =
        MutableStateFlow(CategoriesScreenUiState.Loading(true))
    val uiState: StateFlow<CategoriesScreenUiState> = _uiState

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            _uiState.emit(CategoriesScreenUiState.Loading(true))
            val result = getCategoriesUseCase.invoke()
            result.map {
                Log.d("TAG", "getCategories: ${it.toString()}")

                if (it.isEmpty()) _uiState.emit(CategoriesScreenUiState.Error("Empty list"))
                else _uiState.emit(CategoriesScreenUiState.ShowCategories(it))
            }
        }
    }
}

sealed class CategoriesScreenUiState {
    class Loading(val value: Boolean) : CategoriesScreenUiState()
    class Error(val error: String) : CategoriesScreenUiState()
    class ShowCategories(val categories: List<CategoryModel?>?) :
        CategoriesScreenUiState()
}