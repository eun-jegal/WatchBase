package com.example.watchbase.data.remote

import com.example.watchbase.data.model.movie.MovieList
import com.example.watchbase.data.model.movie.NowPlayingMovieList
import com.example.watchbase.data.model.movie.TrendingMovieList
import com.example.watchbase.data.model.movie.UpcomingMovieList
import com.example.watchbase.data.model.tvshow.TrendingTvShowList
import com.example.watchbase.data.model.tvshow.TvShowList
import retrofit2.Response

interface WatchBaseRemoteDataSource {
    suspend fun getTopTrendTvShows(): Response<TvShowList>
    suspend fun getPopularTvShows(): Response<TvShowList>
    suspend fun getTrendingTvShows(): Response<TrendingTvShowList>
    suspend fun getNowPlayingTvShows(): Response<TvShowList>
    suspend fun getTopTrendMovies(): Response<MovieList>
    suspend fun getPopularMovies(): Response<MovieList>
    suspend fun getTrendingMovies(): Response<TrendingMovieList>
    suspend fun getNowPlayingMovies(): Response<NowPlayingMovieList>
    suspend fun getUpcomingMovies(): Response<UpcomingMovieList>
}