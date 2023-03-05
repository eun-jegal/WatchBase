package com.example.watchbase.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.watchbase.data.repository.GenreRepository
import com.example.watchbase.data.repository.ShowRepository
import com.example.watchbase.ui.viewmodel.HomeViewModel

class HomeViewModelFactory(
    private val showRepository: ShowRepository,
    private val genreRepository: GenreRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(showRepository, genreRepository) as T
    }
}