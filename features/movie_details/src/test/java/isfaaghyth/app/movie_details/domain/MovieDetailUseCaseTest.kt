package isfaaghyth.app.movie_details.domain

import isfaaghyth.app.abstraction.util.ResultState
import isfaaghyth.app.data.entity.Movie
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

    @Before fun setUp() {
        useCase = MovieDetailUseCase(repository)
    }

    @Test fun `should show movie detail response`() {
        val movieId = "60735"
        val apiKey = "123"

        val returnValue = ResultState.Success(movie)

        val request = runBlocking {
            `when`(repository.testGetMovieDetail(movieId)).thenReturn(
                Response.success(movie)
            )
            useCase.testGetMovieDetail(movieId)
        }

        println(returnValue.data)
        println(request)

        assert(returnValue == request)
    }

}