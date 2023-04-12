package com.example.watchbase.data.repository

import androidx.paging.PagingData
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.model.ShowList
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getSearchResult(query: String): Flow<PagingData<Show>>
    suspend fun getTopSearches(mediaType: String, timeWindow: String): Flow<ShowList>
}