package com.example.watchbase.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.watchbase.data.Resource
import com.example.watchbase.data.model.Genre
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.model.ShowType
import com.example.watchbase.data.repository.GenreRepository
import com.example.watchbase.data.repository.ShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(
    private val showRepository: ShowRepository,
    private val genreRepository: GenreRepository
) : ViewModel() {
    var selectedGenre: MutableState<Genre> = mutableStateOf(Genre(null, "Genre"))
    var selectedShowType: MutableState<ShowType> = mutableStateOf(ShowType.TV_SHOW)
    var selectedShows: Stack<Show> = Stack()
    val selectedShow: MutableState<Show?> = mutableStateOf(null)

    private val _genres = mutableStateListOf<Genre>()
    val genres: SnapshotStateList<Genre> = _genres

    private var _trendingShows = mutableStateOf<Flow<PagingData<Show>>>(emptyFlow())
    val trendingShows: State<Flow<PagingData<Show>>> = _trendingShows

    private var _topRatedShows = mutableStateOf<Flow<PagingData<Show>>>(emptyFlow())
    val topRatedShows: State<Flow<PagingData<Show>>> = _topRatedShows

    private var _popularShows = mutableStateOf<Flow<PagingData<Show>>>(emptyFlow())
    val popularShows: State<Flow<PagingData<Show>>> = _popularShows

    private var _nowPlayingShows = mutableStateOf<Flow<PagingData<Show>>>(emptyFlow())
    val nowPlayingShows: State<Flow<PagingData<Show>>> = _nowPlayingShows

    private var _upcomingMovies = mutableStateOf<Flow<PagingData<Show>>>(emptyFlow())
    val upcomingMovies: State<Flow<PagingData<Show>>> = _upcomingMovies

    init {
        fetchData()
    }

    fun fetchData(
        showType: ShowType = selectedShowType.value,
        genreId: Int? = selectedGenre.value.id
    ) {
        if (genres.isEmpty()) {
            getGenreList()
        }
        getTrendingShows(genreId, showType)
        getTopRatedShows(genreId, showType)
        getPopularShows(genreId, showType)
        getNowPlayingShows(genreId, showType)
        if (showType == ShowType.MOVIE) {
            getUpcomingMovies(genreId)
        }
    }

    fun addSelectedShow(show: Show) {
        selectedShows.add(show)
        selectedShow.value = selectedShows.peek()
    }

    fun navigateUp() {
        selectedShows.pop()
        selectedShow.value = selectedShows.peek()
    }

    fun clearSelectedShows() {
        selectedShows.clear()
        selectedShow.value = null
    }

    private fun getGenreList() {
        viewModelScope.launch(Dispatchers.IO) {
            val defaultGenre = Genre(null, "All")
            when (val genreList = genreRepository.getGenres(selectedShowType.value)) {
                is Resource.Success -> {
                    _genres.run {
                        clear()
                        add(defaultGenre)
                        genreList.data?.genres?.forEach {
                            add(it)
                        }
                    }
                }
                is Resource.Error -> {

                }
            }
        }
    }

    private fun getTrendingShows(genreId: Int?, showType: ShowType) {
        viewModelScope.launch(Dispatchers.IO) {
            _trendingShows.value = if (genreId != null) {
                showRepository.getTrendingShows(showType).map { results ->
                    results.filter { movie ->
                        movie.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                showRepository.getTrendingShows(showType).cachedIn(viewModelScope)
            }
        }
    }

    private fun getTopRatedShows(genreId: Int?, showType: ShowType) {
        viewModelScope.launch(Dispatchers.IO) {
            _topRatedShows.value = if (genreId != null) {
                showRepository.getTopRatedShows(showType).map { results ->
                    results.filter { movie ->
                        movie.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                showRepository.getTopRatedShows(showType).cachedIn(viewModelScope)
            }
        }
    }

    private fun getPopularShows(genreId: Int?, showType: ShowType) {
        viewModelScope.launch(Dispatchers.IO) {
            _popularShows.value = if (genreId != null) {
                showRepository.getPopularShows(showType).map { results ->
                    results.filter { movie ->
                        movie.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                showRepository.getPopularShows(showType).cachedIn(viewModelScope)
            }
        }
    }

    private fun getNowPlayingShows(genreId: Int?, showType: ShowType) {
        viewModelScope.launch(Dispatchers.IO) {
            _nowPlayingShows.value = if (genreId != null) {
                showRepository.getNowPlayingShows(showType).map { results ->
                    results.filter { movie ->
                        movie.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                showRepository.getNowPlayingShows(showType).cachedIn(viewModelScope)
            }
        }

    }

    private fun getUpcomingMovies(genreId: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            _upcomingMovies.value = if (genreId != null) {
                showRepository.getUpcomingMovies().map { results ->
                    results.filter { movie ->
                        movie.genreIds!!.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                showRepository.getUpcomingMovies().cachedIn(viewModelScope)
            }
        }

    }
}