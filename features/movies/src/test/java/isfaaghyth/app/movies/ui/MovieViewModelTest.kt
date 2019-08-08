package isfaaghyth.app.movies.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import isfaaghyth.app.abstraction.util.thread.TestSchedulerProvider
import isfaaghyth.app.data.Movie
import isfaaghyth.app.data.Movies
import isfaaghyth.app.movies.domain.MovieUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.given
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule val instantExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var results: Observer<List<Movie>>
    @Mock lateinit var useCase: MovieUseCase

    @Captor lateinit var argCaptor: ArgumentCaptor<List<Movie>>

    private lateinit var viewModel: MovieViewModel

    private val movies = listOf(
        Movie(
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

    private val moviesData = Movies(movies)
    private val schedulerProvider = TestSchedulerProvider()

    @Before fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(schedulerProvider.ui())

        viewModel = MovieViewModel(useCase, schedulerProvider)
        viewModel.result.observeForever(results)
    }

    @Test fun test() {
        runBlocking {
            `when`(useCase.getPopularMovie()).thenReturn(async { MovieState.LoadSuccess(moviesData) }.await())

            viewModel.getPopularMovie()

            verify(results, atLeastOnce()).onChanged(argCaptor.capture())

            val values = argCaptor.allValues

            // Test obtained values in order
            Assert.assertEquals(1, values.size)
            Assert.assertEquals(MovieState.LoadSuccess(moviesData), values[0])
        }
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
    }
}