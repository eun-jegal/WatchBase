package com.example.watchbase.data.repository

import androidx.paging.PagingData
import com.example.watchbase.data.model.ShowList
import com.example.watchbase.data.model.ShowType
import com.example.watchbase.data.remote.WatchBaseRemoteDataSource
import kotlinx.coroutines.flow.Flow

class ShowRepositoryImpl(
    private val remoteDataSource: WatchBaseRemoteDataSource
): ShowRepository {
    override suspend fun getTopRatedShows(showType: ShowType): Flow<PagingData<ShowList>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularShows(showType: ShowType): Flow<PagingData<ShowList>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTrendingShows(showType: ShowType): Flow<PagingData<ShowList>> {
        TODO("Not yet implemented")
    }

    override suspend fun getNowPlayingShows(showType: ShowType): Flow<PagingData<ShowList>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUpcomingMovies(): Flow<PagingData<ShowList>> {
        TODO("Not yet implemented")
    }

}