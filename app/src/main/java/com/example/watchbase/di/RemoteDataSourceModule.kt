package com.example.watchbase.di

import com.example.watchbase.data.remote.WatchBaseRemoteDataSource
import com.example.watchbase.data.remote.WatchBaseRemoteDataSourceImpl
import com.example.watchbase.data.remote.api.TMDBService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(
        tmdbService: TMDBService
    ): WatchBaseRemoteDataSource {
        return WatchBaseRemoteDataSourceImpl(tmdbService)
    }
}