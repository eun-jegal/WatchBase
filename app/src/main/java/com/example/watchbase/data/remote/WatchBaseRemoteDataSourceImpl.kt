package com.example.watchbase.data.remote

import com.example.watchbase.data.model.CastList
import com.example.watchbase.data.model.GenreList
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.remote.api.TMDBService
import com.example.watchbase.data.model.ShowList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    override suspend fun getTvShowCasts(showId: Int): CastList {
        return tmdbService.getTvShowCasts(showId)
    }

    override suspend fun getSimilarTvShows(showId: Int, page: Int): ShowList {
        return tmdbService.getSimilarTvShows(showId, page)
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

    override suspend fun getMovieCasts(showId: Int): CastList {
        return tmdbService.getMovieCasts(showId)
    }

    override suspend fun getSimilarMovies(showId: Int, page: Int): ShowList {
        return tmdbService.getSimilarMovies(showId, page)
    }

    override suspend fun getTrendingShows(mediaType: String, timeWindow: String): ShowList {
        return tmdbService.getTrendingShows(mediaType, timeWindow)
    }

    override suspend fun getSearchResult(query: String, page: Int): ShowList {
        return tmdbService.getMultiSearchResult(query = query, page = page)
    }
}