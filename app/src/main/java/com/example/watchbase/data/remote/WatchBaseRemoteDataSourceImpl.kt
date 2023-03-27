package com.example.watchbase.data.remote

import com.example.watchbase.data.model.GenreList
import com.example.watchbase.data.remote.api.TMDBService
import com.example.watchbase.data.model.ShowList

class WatchBaseRemoteDataSourceImpl(
    private val tmdbService: TMDBService
) : WatchBaseRemoteDataSource {
    override suspend fun getTopRatedTvShows(page: Int): ShowList {
        return tmdbService.getTopRatedTvShows(page = page)
    }

    override suspend fun getPopularTvShows(page: Int): ShowList {
        return tmdbService.getPopularTvShows(page = page)
    }

    override suspend fun getTrendingTvShows(page: Int): ShowList {
        return tmdbService.getTrendingTvShows(page = page)
    }

    override suspend fun getNowPlayingTvShows(page: Int): ShowList {
        return tmdbService.getNowPlayingTvShows(page = page)
    }

    override suspend fun getTvShowGenres(): GenreList {
        return tmdbService.getTvShowGenres()
    }

    override suspend fun getTopRatedMovies(page: Int): ShowList {
        return tmdbService.getTopRatedMovies(page = page)
    }

    override suspend fun getPopularMovies(page: Int): ShowList {
        return tmdbService.getPopularMovies(page = page)
    }

    override suspend fun getTrendingMovies(page: Int): ShowList {
        return tmdbService.getTrendingMovies(page = page)
    }

    override suspend fun getNowPlayingMovies(page: Int): ShowList {
        return tmdbService.getNowPlayingMovies(page = page)
    }

    override suspend fun getUpcomingMovies(page: Int): ShowList {
        return tmdbService.getUpcomingMovies(page = page)
    }

    override suspend fun getMovieGenres(): GenreList {
        return tmdbService.getMovieGenres()
    }
}