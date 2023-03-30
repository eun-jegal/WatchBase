package com.example.watchbase.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.watchbase.data.repository.ShowRepository

class DetailViewModel(
    private val showRepository: ShowRepository
): ViewModel() {
    var selectedShowId: MutableState<Int> = mutableStateOf(-1)
    var selectedShowType: MutableState<String> = mutableStateOf("TV_SHOW")

    private val _showTitle: MutableState<String> = mutableStateOf("")
    val showTitle: State<String> = _showTitle

    private val _rating: MutableState<String> = mutableStateOf("")
    val rating: State<String> = _rating

    private val _releaseDate: MutableState<String> = mutableStateOf("")
    val releaseDate: State<String> = _releaseDate

    private val _runtime: MutableState<String> = mutableStateOf("")
    val runtime: State<String> = _runtime

    private val _posterPath: MutableState<String> = mutableStateOf("")
    val posterPath: State<String> = _posterPath

    fun updateShowDetail() {
        //ToDo: get details from repository
    }
}