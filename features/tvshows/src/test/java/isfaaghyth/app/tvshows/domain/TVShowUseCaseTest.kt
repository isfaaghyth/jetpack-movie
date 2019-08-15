package isfaaghyth.app.tvshows.domain

import isfaaghyth.app.abstraction.util.state.ResultState
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.data.entity.TVShows
import isfaaghyth.app.data.repository.tvshow.TVShowRepository
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

class TVShowUseCaseTest {

    private var repository = mock(TVShowRepository::class.java)
    private lateinit var usecase: TVShowUseCase

    private val tvshows = listOf(
        TVShow(
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
    )

    @Before fun setUp() {
        usecase = TVShowUseCase(repository)
    }

    @Test fun `should return success`() {
        val actual = ResultState.Success(TVShows(tvshows))
        val result = runBlocking {
            `when`(repository.getPopularTVShow())
                .thenReturn(
                    Response.success(TVShows(tvshows))
                )
            usecase.getPopularTvShow()
        }
        assert(actual.javaClass === result.javaClass)
    }

    @Test fun `should return error`() {
        val actual = ResultState.Error("")
        val result = runBlocking {
            `when`(repository.getPopularTVShow())
                .thenReturn(
                    Response.error(401, ResponseBody.create(MediaType.parse("application/json"), ""))
                )
            usecase.getPopularTvShow()
        }
        assert(actual.javaClass === result.javaClass)
    }

}