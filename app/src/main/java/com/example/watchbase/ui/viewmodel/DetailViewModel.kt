package com.example.watchbase.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.watchbase.data.model.Cast
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.model.ShowType
import com.example.watchbase.data.repository.ShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class DetailViewModel(
    private val showRepository: ShowRepository
): ViewModel() {

    private var _casts = mutableStateOf<List<Cast>>(emptyList())
    val cast: State<List<Cast>> = _casts

    private var _similarShows = mutableStateOf<Flow<PagingData<Show>>>(emptyFlow())
    val similarShows: State<Flow<PagingData<Show>>> = _similarShows

    fun getShowCasts(showId: Int, showType: ShowType) {

    }

    fun getSimilarShows(showId: Int, showType: ShowType) {

    }
}