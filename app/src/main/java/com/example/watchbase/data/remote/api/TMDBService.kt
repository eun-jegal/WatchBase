package com.example.watchbase.data.remote.api

import com.example.watchbase.BuildConfig
import com.example.watchbase.data.model.GenreList
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.model.ShowList
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

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetails(
        @Path("tv_id")
        show_id: Int,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Show

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

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id")
        movie_id: Int,
        @Query("language")
        language: String = "en",
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Show
}