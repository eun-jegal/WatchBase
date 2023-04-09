package com.example.watchbase.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.repository.ShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val showRepository: ShowRepository
): ViewModel() {

    private var _trendingShows = mutableStateOf<List<Show>>(emptyList())
    val trendingShows: State<List<Show>> = _trendingShows

    init {
        getTrendingShows()
    }

    private fun getTrendingShows() {
        viewModelScope.launch(Dispatchers.IO) {
            showRepository.getTrendingShows("all", "week").collect {
                _trendingShows.value = it.results
            }
        }
    }
}