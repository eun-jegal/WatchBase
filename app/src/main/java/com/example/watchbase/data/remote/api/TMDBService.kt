package com.example.watchbase.data.remote.api

import com.example.watchbase.BuildConfig
import com.example.watchbase.data.model.*
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    // Tv Shows
    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("page")
        page: Int = 1,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page")
        page: Int = 1,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("trending/tv/week")
    suspend fun getTrendingTvShows(
        @Query("page")
        page: Int = 1,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("tv/on_the_air")
    suspend fun getNowPlayingTvShows(
        @Query("page")
        page: Int = 1,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("genre/tv/list")
    suspend fun getTvShowGenres(
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): GenreList

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarTvShows(
        @Path("tv_id")
        movieId: Int,
        @Query("page")
        page: Int = 1,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("tv/{tv_id}/credits")
    suspend fun getTvShowCasts(
        @Path("tv_id")
        showId: Int,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): CastList

    // Movies
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page")
        page: Int = 1,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page")
        page: Int = 1,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("page")
        page: Int = 1,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page")
        page: Int = 1,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page")
        page: Int = 1,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): GenreList

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id")
        movieId: Int,
        @Query("page")
        page: Int = 1,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCasts(
        @Path("movie_id")
        showId: Int,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): CastList
}