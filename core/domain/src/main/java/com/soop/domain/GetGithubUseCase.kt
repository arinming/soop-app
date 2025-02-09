package com.soop.domain

import com.soop.data.repository.GithubRepository
import com.soop.model.GithubRepositoryInfo
import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetGithubUseCase @Inject constructor(
    private val githubRepository: GithubRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(query: String): Flow<PagingData<GithubRepositoryInfo>> {
        return withContext(defaultDispatcher) {
            githubRepository.getGithub(query)
                .catch {
                    emit(PagingData.empty())
                }
        }
    }
}
