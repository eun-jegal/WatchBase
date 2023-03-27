package com.example.watchbase.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.watchbase.data.model.Show
import com.example.watchbase.data.remote.WatchBaseRemoteDataSource
import retrofit2.HttpException
import java.io.IOException

class UpcomingMovieSource(
    private val remoteDataSource: WatchBaseRemoteDataSource
) : PagingSource<Int, Show>() {
    override fun getRefreshKey(state: PagingState<Int, Show>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {
        return try {
            val nextPage = params.key ?: 1
            val showList = remoteDataSource.getUpcomingMovies(page = nextPage)

            LoadResult.Page(
                data = showList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (showList.results.isEmpty()) null else showList.page + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}