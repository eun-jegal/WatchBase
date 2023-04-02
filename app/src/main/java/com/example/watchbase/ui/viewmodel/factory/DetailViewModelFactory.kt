package com.example.watchbase.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.watchbase.data.repository.ShowRepository
import com.example.watchbase.ui.viewmodel.DetailViewModel

class DetailViewModelFactory(
    private val showRepository: ShowRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(showRepository) as T
    }
}