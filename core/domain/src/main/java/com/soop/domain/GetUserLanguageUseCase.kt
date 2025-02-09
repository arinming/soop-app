package com.soop.domain

import com.soop.data.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserLanguageUseCase @Inject constructor(
    private val githubRepository: GithubRepository,
) {

    operator fun invoke(owner: String): Flow<String> {
        return githubRepository.getUserLanguage(owner)
            .map { languages ->
                languages.map { it.language }
                    .filter { it.isNotBlank() }
                    .distinct()
                    .joinToString(", ")
            }
    }
}
