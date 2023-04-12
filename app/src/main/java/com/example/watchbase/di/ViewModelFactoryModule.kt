package com.example.watchbase.di

import com.example.watchbase.data.repository.GenreRepository
import com.example.watchbase.data.repository.SearchRepository
import com.example.watchbase.data.repository.ShowRepository
import com.example.watchbase.ui.viewmodel.factory.DetailViewModelFactory
import com.example.watchbase.ui.viewmodel.factory.HomeViewModelFactory
import com.example.watchbase.ui.viewmodel.factory.SearchViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {
    @Singleton
    @Provides
    fun provideHomeViewModelFactory(
        showRepository: ShowRepository,
        genreRepository: GenreRepository
    ): HomeViewModelFactory {
        return HomeViewModelFactory(showRepository, genreRepository)
    }

    @Singleton
    @Provides
    fun provideDetailViewModelFactory(
        showRepository: ShowRepository
    ): DetailViewModelFactory {
        return DetailViewModelFactory(showRepository)
    }

    @Singleton
    @Provides
    fun provideSearchViewModelFactory(
        searchRepository: SearchRepository
    ): SearchViewModelFactory {
        return SearchViewModelFactory(searchRepository)
    }
}