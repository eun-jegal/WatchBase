package com.example.watchbase.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.watchbase.data.repository.ShowRepository
import com.example.watchbase.ui.viewmodel.SearchViewModel

class SearchViewModelFactory(
    private val showRepository: ShowRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(showRepository) as T
    }
}