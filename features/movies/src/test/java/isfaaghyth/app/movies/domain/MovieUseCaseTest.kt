package isfaaghyth.app.movies.domain

import isfaaghyth.app.movies.BuildConfig
import isfaaghyth.app.movies.data.model.Movie
import isfaaghyth.app.movies.data.model.Movies
import isfaaghyth.app.movies.data.repository.MovieRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.CompletableDeferred
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

    @Test fun `should size of result is not empty`() {
        val result = runBlocking {
            `when`(repository.getPopularMovie(BuildConfig.API_KEY))
                .thenReturn(CompletableDeferred(Movies(movies)))
            useCase.getPopularMovie()
        }
        assert(result.resultsIntent.isNotEmpty())
    }

}