package isfaaghyth.app.data.repository.movie_detail

import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.data.routes.NetworkServices
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import retrofit2.Response

class MovieDetailRepositoryTest {

    private var services = mock(NetworkServices::class.java)
    private lateinit var repository: MovieDetailRepository

    private val movieId = "1"

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
        repository = MovieDetailRepositoryImpl(services)
    }

    @Test fun `should get popular tv show detail success`() = runBlocking {
        Mockito.`when`(services.getTvDetail(movieId))
            .thenReturn(
                Response.success(tvshow)
            )
        val repo = repository.getTVShowDetail(movieId)
        assert(repo.body() === tvshow)
    }

    @Test fun `should get popular movie detail success`() = runBlocking {
        Mockito.`when`(services.getMovieDetail(movieId))
            .thenReturn(
                Response.success(movie)
            )
        val repo = repository.getMovieDetail(movieId)
        assert(repo.body() === movie)
    }

    @Test fun `should get null when getting tv show detail and error`() = runBlocking {
        Mockito.`when`(services.getTvDetail(movieId))
            .thenReturn(
                Response.error(401, ResponseBody.create(MediaType.parse("application/json"), ""))
            )
        val repo = repository.getTVShowDetail(movieId)
        assert(repo.body() === null)
    }

    @Test fun `should get null when getting movie detail and error`() = runBlocking {
        Mockito.`when`(services.getMovieDetail(movieId))
            .thenReturn(
                Response.error(401, ResponseBody.create(MediaType.parse("application/json"), ""))
            )
        val repo = repository.getMovieDetail(movieId)
        assert(repo.body() === null)
    }

}