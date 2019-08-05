package isfaaghyth.app.movies.data.repository

import com.nhaarman.mockitokotlin2.whenever
import isfaaghyth.app.movies.data.MovieService
import isfaaghyth.app.movies.data.model.Movie
import isfaaghyth.app.movies.data.model.Movies
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class MovieRepositoryImplTest {

    private val services = mock(MovieService::class.java)
    private lateinit var repository: MovieRepository

    @Before fun setUp() {
        repository = MovieRepositoryImpl(services)
    }

    @Test fun testExample() {
        val movieMock = Movie("id",
            "movieId",
            "title",
            "posterPath",
            "overview",
            "backdrop",
            "relateDate")

        val movies = arrayListOf<Movie>()
        movies.add(movieMock)

        val movie = Movies(movies)

        runBlocking {
            whenever(repository.getPopularMovie("").await()).thenReturn(movie)
            verify(repository.getPopularMovie("").await()).resultsIntent
        }
    }

}