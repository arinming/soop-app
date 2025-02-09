package com.soop.domain

import com.soop.data.repository.GithubRepository
import com.soop.model.RepositoryDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoryDetailUseCase @Inject constructor(
    private val githubRepository: GithubRepository,
) {
    operator fun invoke(owner: String, repo: String): Flow<RepositoryDetail> {
        return githubRepository.getRepositoryDetail(owner, repo)
    }
}
