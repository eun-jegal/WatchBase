package com.example.watchbase.data.api

import com.example.watchbase.BuildConfig
import com.example.watchbase.data.model.ShowList
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("page")
        page: Int = 0,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page")
        page: Int = 0,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("trending/tv/week")
    suspend fun getTrendingTvShows(
        @Query("page")
        page: Int = 0,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("/tv/on_the_air")
    suspend fun getNowPlayingTvShows(
        @Query("page")
        page: Int = 0,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page")
        page: Int = 0,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page")
        page: Int = 0,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("page")
        page: Int = 0,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page")
        page: Int = 0,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

    @GET("/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page")
        page: Int = 0,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): ShowList

}