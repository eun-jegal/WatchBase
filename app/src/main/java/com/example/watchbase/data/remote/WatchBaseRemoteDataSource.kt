package com.example.watchbase.data.remote

import com.example.watchbase.data.model.GenreList
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.model.ShowList
import kotlinx.coroutines.flow.Flow

interface WatchBaseRemoteDataSource {
    suspend fun getTopRatedTvShows(page: Int): ShowList
    suspend fun getPopularTvShows(page: Int): ShowList
    suspend fun getTrendingTvShows(page: Int): ShowList
    suspend fun getNowPlayingTvShows(page: Int): ShowList
    suspend fun getTvShowGenres(): GenreList
    suspend fun getTvShowDetails(showId: Int): Flow<Show>

    suspend fun getTopRatedMovies(page: Int): ShowList
    suspend fun getPopularMovies(page: Int): ShowList
    suspend fun getTrendingMovies(page: Int): ShowList
    suspend fun getNowPlayingMovies(page: Int): ShowList
    suspend fun getUpcomingMovies(page: Int): ShowList
    suspend fun getMovieGenres(): GenreList
    suspend fun getMovieDetails(showId: Int): Flow<Show>
}