package com.example.watchbase.data.repository

import androidx.paging.PagingData
import com.example.watchbase.data.model.ShowList
import com.example.watchbase.data.model.ShowType
import kotlinx.coroutines.flow.Flow

interface ShowRepository {
    suspend fun getTopRatedShows(showType: ShowType): Flow<PagingData<ShowList>>
    suspend fun getPopularShows(showType: ShowType): Flow<PagingData<ShowList>>
    suspend fun getTrendingShows(showType: ShowType): Flow<PagingData<ShowList>>
    suspend fun getNowPlayingShows(showType: ShowType): Flow<PagingData<ShowList>>
    suspend fun getUpcomingMovies(): Flow<PagingData<ShowList>>
}