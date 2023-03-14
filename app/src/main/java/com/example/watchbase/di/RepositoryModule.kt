package com.example.watchbase.di

import com.example.watchbase.data.remote.WatchBaseRemoteDataSource
import com.example.watchbase.data.repository.GenreRepository
import com.example.watchbase.data.repository.GenreRepositoryImpl
import com.example.watchbase.data.repository.ShowRepository
import com.example.watchbase.data.repository.ShowRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideShowRepository(
        remoteDataSource: WatchBaseRemoteDataSource
    ): ShowRepository {
        return ShowRepositoryImpl(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideGenreRepository(
        remoteDataSource: WatchBaseRemoteDataSource
    ): GenreRepository {
        return GenreRepositoryImpl(remoteDataSource)
    }
}