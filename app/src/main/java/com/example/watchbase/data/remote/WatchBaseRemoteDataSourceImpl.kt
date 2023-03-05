package com.example.watchbase.data.remote

import com.example.watchbase.data.api.TMDBService
import com.example.watchbase.data.model.ShowList
import retrofit2.Response

class WatchBaseRemoteDataSourceImpl(
    private val tmdbService: TMDBService
) : WatchBaseRemoteDataSource {
    override suspend fun getTopRatedTvShows(): Response<ShowList> {
        return tmdbService.getTopRatedTvShows()
    }

    override suspend fun getPopularTvShows(): Response<ShowList> {
        return tmdbService.getPopularTvShows()
    }

    override suspend fun getTrendingTvShows(): Response<ShowList> {
        return tmdbService.getTrendingTvShows()
    }

    override suspend fun getNowPlayingTvShows(): Response<ShowList> {
        return tmdbService.getNowPlayingTvShows()
    }

    override suspend fun getTopRatedMovies(): Response<ShowList> {
        return tmdbService.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): Response<ShowList> {
        return tmdbService.getPopularMovies()
    }

    override suspend fun getTrendingMovies(): Response<ShowList> {
        return tmdbService.getTrendingMovies()
    }

    override suspend fun getNowPlayingMovies(): Response<ShowList> {
        return tmdbService.getNowPlayingMovies()
    }

    override suspend fun getUpcomingMovies(): Response<ShowList> {
        return tmdbService.getUpcomingMovies()
    }
}