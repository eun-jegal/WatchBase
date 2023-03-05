package com.example.watchbase.data.repository

import com.example.watchbase.data.Resource
import com.example.watchbase.data.model.GenreList
import com.example.watchbase.data.model.ShowType
import com.example.watchbase.data.remote.WatchBaseRemoteDataSource
import java.lang.Exception

class GenreRepositoryImpl(
    private val remoteDataSource: WatchBaseRemoteDataSource
) : GenreRepository {
    override suspend fun getGenres(showType: ShowType): Resource<GenreList> {
        val response = try {
            if (showType == ShowType.TV_SHOW) remoteDataSource.getTvShowGenres() else remoteDataSource.getMovieGenres()
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred!")
        }
        return Resource.Success(response)
    }
}