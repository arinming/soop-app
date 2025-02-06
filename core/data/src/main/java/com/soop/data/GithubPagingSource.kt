package com.soop.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.soop.model.GithubRepositoryInfo
import com.soop.network.GithubDataSource
import com.soop.network.model.NetworkGithubData
import com.soop.network.model.asExternalModel


class GithubPagingSource(
    private val query: String,
    private val dataSource: GithubDataSource
) : PagingSource<Int, GithubRepositoryInfo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepositoryInfo> {
        return try {
            val nextPage = params.key ?: 1

            val response: NetworkGithubData = dataSource.getGithub(
                page = nextPage,
                perPage = params.loadSize,
                query = query
            )

            val repositories = response.asExternalModel()

            LoadResult.Page(
                data = repositories,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (repositories.isEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubRepositoryInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
