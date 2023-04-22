package com.example.watchbase.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private var _topSearchShows = mutableStateOf<List<Show>>(emptyList())
    val topSearchShows: State<List<Show>> = _topSearchShows

    private var _searchResult = mutableStateOf<Flow<PagingData<Show>>>(emptyFlow())
    val searchResult: State<Flow<PagingData<Show>>> = _searchResult

    var searchQuery = mutableStateOf("")

    init {
        getTrendingShows()
    }

    private fun getTrendingShows() {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.getTopSearches("all", "week").collect {
                _topSearchShows.value = it.results
            }
        }
    }

    fun search() {
        viewModelScope.launch(Dispatchers.IO) {
            if (searchQuery.value.isNotEmpty()) {
                _searchResult.value =
                    searchRepository.getSearchResult(searchQuery.value).map { result ->
                        result.filter {
                            it.posterPath != null && (it.mediaType == "tv" || it.mediaType == "movie")
                        }
                    }.cachedIn(viewModelScope)
            }
        }
    }

    fun clearSearchResult() {
        _searchResult.value = emptyFlow()
    }
}