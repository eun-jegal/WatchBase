package com.example.watchbase.di

import com.example.watchbase.data.remote.WatchBaseRemoteDataSource
import com.example.watchbase.data.repository.*
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

    @Singleton
    @Provides
    fun provideSearchRepository(
        remoteDataSource: WatchBaseRemoteDataSource
    ): SearchRepository {
        return SearchRepositoryImpl(remoteDataSource)
    }
}