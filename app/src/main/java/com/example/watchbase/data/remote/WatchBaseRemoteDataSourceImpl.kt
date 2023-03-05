package com.example.watchbase.data.remote

import com.example.watchbase.data.api.TMDBService
import com.example.watchbase.data.model.movie.MovieList
import com.example.watchbase.data.model.movie.NowPlayingMovieList
import com.example.watchbase.data.model.movie.TrendingMovieList
import com.example.watchbase.data.model.movie.UpcomingMovieList
import com.example.watchbase.data.model.tvshow.TrendingTvShowList
import com.example.watchbase.data.model.tvshow.TvShowList
import retrofit2.Response

class WatchBaseRemoteDataSourceImpl(
    private val tmdbService: TMDBService
) : WatchBaseRemoteDataSource {
    override suspend fun getTopTrendTvShows(): Response<TvShowList> {
        return tmdbService.getTopRatedTvShows()
    }

    override suspend fun getPopularTvShows(): Response<TvShowList> {
        return tmdbService.getPopularTvShows()
    }

    override suspend fun getTrendingTvShows(): Response<TrendingTvShowList> {
        return tmdbService.getTrendingTvShows()
    }

    override suspend fun getNowPlayingTvShows(): Response<TvShowList> {
        return tmdbService.getNowPlayingTvShows()
    }

    override suspend fun getTopTrendMovies(): Response<MovieList> {
        return tmdbService.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): Response<MovieList> {
        return tmdbService.getPopularMovies()
    }

    override suspend fun getTrendingMovies(): Response<TrendingMovieList> {
        return tmdbService.getTrendingMovies()
    }

    override suspend fun getNowPlayingMovies(): Response<NowPlayingMovieList> {
        return tmdbService.getNowPlayingMovies()
    }

    override suspend fun getUpcomingMovies(): Response<UpcomingMovieList> {
        return tmdbService.getUpcomingMovies()
    }
}