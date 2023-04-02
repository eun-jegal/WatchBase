package com.example.watchbase.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.model.ShowType
import com.example.watchbase.data.remote.WatchBaseRemoteDataSource
import com.example.watchbase.pagination.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ShowRepositoryImpl(
    private val remoteDataSource: WatchBaseRemoteDataSource
) : ShowRepository {
    override suspend fun getTopRatedShows(showType: ShowType): Flow<PagingData<Show>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TopRatedShowSource(remoteDataSource, showType)
            }
        ).flow
    }

    override suspend fun getPopularShows(showType: ShowType): Flow<PagingData<Show>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                PopularShowSource(remoteDataSource, showType)
            }
        ).flow
    }

    override suspend fun getTrendingShows(showType: ShowType): Flow<PagingData<Show>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TrendingShowSource(remoteDataSource, showType)
            }
        ).flow
    }

    override suspend fun getNowPlayingShows(showType: ShowType): Flow<PagingData<Show>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                NowPlayingShowSource(remoteDataSource, showType)
            }
        ).flow
    }

    override suspend fun getUpcomingMovies(): Flow<PagingData<Show>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                UpcomingMovieSource(remoteDataSource)
            }
        ).flow
    }

    override suspend fun getShowDetails(showId: Int, showType: ShowType): Flow<Show> {
        return if (showType == ShowType.TV_SHOW) {
            remoteDataSource.getTvShowDetails(showId)
        } else {
            remoteDataSource.getMovieDetails(showId)
        }
    }

}