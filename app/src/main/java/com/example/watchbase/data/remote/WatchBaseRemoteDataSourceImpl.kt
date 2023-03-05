package com.example.watchbase.data.remote

import com.example.watchbase.data.api.TMDBService
import com.example.watchbase.data.model.ShowList

class WatchBaseRemoteDataSourceImpl(
    private val tmdbService: TMDBService
) : WatchBaseRemoteDataSource {
    override suspend fun getTopRatedTvShows(): ShowList {
        return tmdbService.getTopRatedTvShows()
    }

    override suspend fun getPopularTvShows(): ShowList {
        return tmdbService.getPopularTvShows()
    }

    override suspend fun getTrendingTvShows(): ShowList {
        return tmdbService.getTrendingTvShows()
    }

    override suspend fun getNowPlayingTvShows(): ShowList {
        return tmdbService.getNowPlayingTvShows()
    }

    override suspend fun getTopRatedMovies(): ShowList {
        return tmdbService.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): ShowList {
        return tmdbService.getPopularMovies()
    }

    override suspend fun getTrendingMovies(): ShowList {
        return tmdbService.getTrendingMovies()
    }

    override suspend fun getNowPlayingMovies(): ShowList {
        return tmdbService.getNowPlayingMovies()
    }

    override suspend fun getUpcomingMovies(): ShowList {
        return tmdbService.getUpcomingMovies()
    }
}