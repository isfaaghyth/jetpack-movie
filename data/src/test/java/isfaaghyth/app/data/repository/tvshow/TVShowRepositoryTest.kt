package isfaaghyth.app.data.repository.tvshow

import isfaaghyth.app.data.entity.TVShows
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.data.service.NetworkServices
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

class TVShowRepositoryTest {

    private var services = Mockito.mock(NetworkServices::class.java)
    private lateinit var repository: TVShowRepository

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
        repository = TVShowRepositoryImpl(services)
    }

    @Test fun `should get popular tv show success`() = runBlocking {
        Mockito.`when`(services.getPopularTVShow()).thenReturn(
            Response.success(TVShows(tvshows))
        )
        val repo = repository.getPopularTVShow()

        Assert.assertEquals(repo.body(), TVShows(tvshows))
    }

    @Test fun `should get null and error`() = runBlocking {
        Mockito.`when`(services.getPopularTVShow()).thenReturn(
            Response.error(401, ResponseBody.create(MediaType.parse("application/json"), ""))
        )
        val repo = repository.getPopularTVShow()

        Assert.assertEquals(repo.body(), null)
    }
}