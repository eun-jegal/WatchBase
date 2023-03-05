package com.example.watchbase.data.api

import com.example.watchbase.BuildConfig
import com.example.watchbase.data.model.movie.MovieList
import com.example.watchbase.data.model.movie.NowPlayingMovieList
import com.example.watchbase.data.model.movie.TrendingMovieList
import com.example.watchbase.data.model.movie.UpcomingMovieList
import com.example.watchbase.data.model.tvshow.TrendingTvShowList
import com.example.watchbase.data.model.tvshow.TvShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response<TvShowList>

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response<TvShowList>

    @GET("trending/tv/week")
    suspend fun getTrendingTvShows(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response<TrendingTvShowList>

    @GET("/tv/on_the_air")
    suspend fun getNowPlayingTvShows(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response<TvShowList>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response<MovieList>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response<MovieList>

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response<TrendingMovieList>

    @GET("/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response<NowPlayingMovieList>

    @GET("/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Response<UpcomingMovieList>

}