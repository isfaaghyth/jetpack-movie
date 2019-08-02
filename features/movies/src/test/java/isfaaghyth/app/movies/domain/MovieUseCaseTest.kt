package isfaaghyth.app.movies.domain

import com.nhaarman.mockitokotlin2.whenever
import isfaaghyth.app.movies.data.model.Movie
import isfaaghyth.app.movies.data.model.Movies
import isfaaghyth.app.movies.data.repository.MovieRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class MovieUseCaseTest {

    private var repository = mock(MovieRepository::class.java)
    private lateinit var useCase: MovieUseCase

    private val movies = listOf(
        Movie("id",
            "movieId",
            "title",
            "posterPath",
            "overview",
            "backdrop",
            "relateDate")
    )

    @Before fun setUp() {
        useCase = MovieUseCase(repository)
    }

    @Test fun `should return movie response`() = runBlocking {
        whenever(useCase.getPopularMovie()).thenReturn(Movies(movies))
        verify(useCase, atLeastOnce()).getPopularMovie().resultsIntent
        verifyNoMoreInteractions(useCase)
        clearInvocations(useCase)
    }

}