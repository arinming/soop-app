import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.soop.domain.GetGithubUseCase
import com.soop.model.GithubRepositoryInfo
import com.soop.search.SearchUiState
import com.soop.search.SearchViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.whenever
import kotlin.test.assertIs

class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel
    private lateinit var githubUseCase: GetGithubUseCase

    @Before
    fun setup() {
        githubUseCase = mock(GetGithubUseCase::class.java)
        viewModel = SearchViewModel(
            getGithubUseCase = githubUseCase,
            savedStateHandle = mock(SavedStateHandle::class.java)
        )
    }

    @Test
    fun `initial state should be empty`() {
        assertIs<SearchUiState.Empty>(viewModel.searchUiState.value)
    }

    @Test
    fun `search query is empty, should return EmptyQuery state`() = runTest {
        viewModel.onSearchQueryChanged("")
        assertIs<SearchUiState.Empty>(viewModel.searchUiState.value)
    }

    @Test
    fun `search with valid query should return results`() = runTest {
        // Given
        val fakeRepositories = listOf(
            GithubRepositoryInfo(
                username = "google",
                name = "android",
                fullName = "google/android",
                avatarUrl = "https://avatars.githubusercontent.com/u/1342004?v=4",
                description = "Android Open Source Project",
                stars = 100000,
                language = "Kotlin"
            )
        )
        val fakePagingData = PagingData.from(fakeRepositories)

        whenever(githubUseCase("android")).thenReturn(flowOf(fakePagingData))

        // When
        viewModel.onSearchTriggered("android")

        // Then
        val result = viewModel.searchUiState.value
        assertIs<SearchUiState.Success>(result)
    }

    @Test
    fun `search with invalid query should return empty result`() = runTest {
        // Given
        whenever(githubUseCase("invalid_query")).thenReturn(flowOf(PagingData.empty()))

        // When
        viewModel.onSearchTriggered("invalid_query")

        // Then
        val result = viewModel.searchUiState.value
        assertIs<SearchUiState.Success>(result)
    }
}
