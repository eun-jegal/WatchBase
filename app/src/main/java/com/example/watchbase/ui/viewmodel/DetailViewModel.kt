package com.example.watchbase.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.watchbase.data.model.Cast
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.model.ShowType
import com.example.watchbase.data.repository.ShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val showRepository: ShowRepository
) : ViewModel() {

    private var _casts = mutableStateOf<List<Cast>>(emptyList())
    val cast: State<List<Cast>> = _casts

    private var _similarShows = mutableStateOf<Flow<PagingData<Show>>>(emptyFlow())
    val similarShows: State<Flow<PagingData<Show>>> = _similarShows

    fun getShowCasts(showId: Int, showType: ShowType) {
        viewModelScope.launch(Dispatchers.IO) {
            showRepository.getShowCasts(showId, showType).collect {
                _casts.value = it.cast
            }
        }
    }

    fun getSimilarShows(showId: Int, showType: ShowType) {
        viewModelScope.launch(Dispatchers.IO) {
            _similarShows.value =
                showRepository.getSimilarShows(showId, showType).cachedIn(viewModelScope)
        }
    }
}