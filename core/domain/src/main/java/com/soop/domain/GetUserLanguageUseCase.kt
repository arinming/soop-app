package com.soop.domain

import com.soop.data.repository.GithubRepository
import com.soop.model.UserLanguage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserLanguageUseCase @Inject constructor(
    private val githubRepository: GithubRepository,
) {

    operator fun invoke(owner: String): Flow<List<UserLanguage>> {
        return githubRepository.getUserLanguage(owner)
    }
}
