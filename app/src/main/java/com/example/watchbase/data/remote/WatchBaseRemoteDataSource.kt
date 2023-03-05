package com.example.watchbase.data.remote

import com.example.watchbase.data.model.ShowList
import retrofit2.Response

interface WatchBaseRemoteDataSource {
    suspend fun getTopRatedTvShows(): Response<ShowList>
    suspend fun getPopularTvShows(): Response<ShowList>
    suspend fun getTrendingTvShows(): Response<ShowList>
    suspend fun getNowPlayingTvShows(): Response<ShowList>
    suspend fun getTopRatedMovies(): Response<ShowList>
    suspend fun getPopularMovies(): Response<ShowList>
    suspend fun getTrendingMovies(): Response<ShowList>
    suspend fun getNowPlayingMovies(): Response<ShowList>
    suspend fun getUpcomingMovies(): Response<ShowList>
}