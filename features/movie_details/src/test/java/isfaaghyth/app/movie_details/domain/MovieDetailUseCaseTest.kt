package isfaaghyth.app.movie_details.domain

import isfaaghyth.app.abstraction.util.state.ResultState
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.data.repository.movie_detail.MovieDetailRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

@ExperimentalCoroutinesApi
class MovieDetailUseCaseTest {

    private var repository = mock(MovieDetailRepository::class.java)
    private lateinit var useCase: MovieDetailUseCase

    @Before fun setUp() {
        useCase = MovieDetailUseCase(repository)
    }

    @Test fun `should show movie detail response`() {
        val movieId = "60735"
        val returnValue = ResultState.Success(movie)
        val request = runBlocking {
            `when`(repository.getMovieDetail(movieId)).thenReturn(
                Response.success(movie)
            )
            useCase.getMovieDetail(movieId)
        }
        assert(returnValue == request)
    }

    @Test fun `should show tv detail response`() {
        val movieId = "60735"
        val returnValue = ResultState.Success(tvshow)
        val request = runBlocking {
            `when`(repository.getTVShowDetail(movieId)).thenReturn(
                Response.success(tvshow)
            )
            useCase.getTvDetail(movieId)
        }
        assert(returnValue == request)
    }

    companion object {
        private val movie = Movie(
            "id",
            "movieId",
            "title",
            "posterPath",
            "overview",
            "backdrop",
            0,
            0f,
            "relateDate"
        )

        private val tvshow = TVShow(
            "id",
            "movieId",
            "title",
            "posterPath",
            "overview",
            "backdrop",
            0,
            0f,
            "relateDate"
        )
    }

}