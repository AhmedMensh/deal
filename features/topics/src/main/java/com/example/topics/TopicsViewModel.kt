package com.example.topics

import com.example.domain.GetTopicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


@HiltViewModel
class TopicsViewModel @Inject constructor(
    private val getTopicsUseCase: GetTopicsUseCase
) {

//    val feedUiState: StateFlow<TopicsUiState> = getTopicsUseCase()
//        .filterNot { it.isEmpty() }
////        .map { newsResources -> newsResources.filter(SaveableNewsResource::isSaved) } // Only show bookmarked news resources.
////        .map<List<SaveableNewsResource>, NewsFeedUiState>(NewsFeedUiState::Success)
//        .onStart { emit(TopicsUiState.Loading) }
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = TopicsUiState.Loading
//        )
//
//
//    fun getTopics() {
//
//    }

//    val topicsUiState: StateFlow<TopicsUiState> =
//        getTopicsUseCase().onStart {  }

}


sealed interface TopicsUiState {
    object Loading : TopicsUiState
    data class Success(val feed: List<String>) : TopicsUiState
}