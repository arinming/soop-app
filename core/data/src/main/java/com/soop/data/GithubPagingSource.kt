package com.soop.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.soop.network.GithubDataSource
import com.soop.network.model.Repository

class GithubPagingSource(
    private val query: String,
    private val dataSource: GithubDataSource
) : PagingSource<Int, Pair<Int, Repository>>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pair<Int, Repository>> {
        return try {
            val nextPage = params.key ?: 1

            val response = dataSource.getGithub(
                page = nextPage,
                query = query
            )

            LoadResult.Page(
                data = response.repositories.mapIndexed { index, repository ->
                    Pair(nextPage + index, repository)
                },
                prevKey = null,
                nextKey = if (response.repositories.isEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pair<Int, Repository>>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
