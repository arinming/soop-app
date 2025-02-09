package com.soop.domain

import com.soop.data.repository.GithubRepository
import com.soop.model.UserDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val githubRepository: GithubRepository,
) {

    operator fun invoke(owner: String): Flow<UserDetail> {
        return githubRepository.getUserDetail(owner)
    }
}
