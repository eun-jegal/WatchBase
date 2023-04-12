package com.example.watchbase.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.model.ShowList
import com.example.watchbase.data.remote.WatchBaseRemoteDataSource
import com.example.watchbase.pagination.SearchedShowSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl(
    private val remoteDataSource: WatchBaseRemoteDataSource
): SearchRepository {
    override suspend fun getSearchResult(query: String): Flow<PagingData<Show>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                SearchedShowSource(remoteDataSource, query)
            }
        ).flow
    }

    override suspend fun getTopSearches(mediaType: String, timeWindow: String): Flow<ShowList> {
        return flow {
            emit(remoteDataSource.getTrendingShows(mediaType, timeWindow))
        }
    }
}