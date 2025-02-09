package com.soop.domain

import androidx.paging.PagingData
import com.soop.data.repository.GithubRepository
import com.soop.model.GithubRepositoryInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGithubUseCase @Inject constructor(
    private val githubRepository: GithubRepository,
) {


    operator fun invoke(query: String): Flow<PagingData<GithubRepositoryInfo>> {
        return githubRepository.getGithub(query)
    }
}
