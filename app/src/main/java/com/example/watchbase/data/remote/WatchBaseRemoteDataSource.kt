package com.example.watchbase.data.remote

import com.example.watchbase.data.model.GenreList
import com.example.watchbase.data.model.ShowList

interface WatchBaseRemoteDataSource {
    suspend fun getTopRatedTvShows(): ShowList
    suspend fun getPopularTvShows(): ShowList
    suspend fun getTrendingTvShows(): ShowList
    suspend fun getNowPlayingTvShows(): ShowList
    suspend fun getTvShowGenres(): GenreList

    suspend fun getTopRatedMovies(): ShowList
    suspend fun getPopularMovies(): ShowList
    suspend fun getTrendingMovies(): ShowList
    suspend fun getNowPlayingMovies(): ShowList
    suspend fun getUpcomingMovies(): ShowList
    suspend fun getMovieGenres(): GenreList
}