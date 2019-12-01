package isfaaghyth.app.movies.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import isfaaghyth.app.abstraction.util.state.ResultState
import isfaaghyth.app.abstraction.util.thread.TestSchedulerProvider
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.Movies
import isfaaghyth.app.movies.domain.MovieUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MovieViewModelTest {

    @get:Rule val instantExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var result: Observer<List<Movie>>
    @Mock lateinit var error: Observer<String>

    @Captor lateinit var argResultCaptor: ArgumentCaptor<List<Movie>>
    @Captor lateinit var argErrorCaptor: ArgumentCaptor<String>

    @Mock lateinit var useCase: MovieUseCase

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
        viewModel.result.observeForever(result)
        viewModel.error.observeForever(error)
    }

    @Test fun `should return a response of movies data`() = runBlocking {
        val returnValue = ResultState.Success(moviesData)
        `when`(useCase.getPopularMovie()).thenReturn(returnValue)
        viewModel.getPopularMovie()
        verify(result, atLeastOnce()).onChanged(argResultCaptor.capture())
        assertEquals(returnValue.data.resultsIntent, argResultCaptor.allValues.first())
        clearInvocations(useCase, result)
    }

    @Test fun `should return an error without api key`() = runBlocking {
        val returnValue = ResultState.Error("API Key Not Found")
        `when`(useCase.getPopularMovie()).thenReturn(returnValue)
        viewModel.getPopularMovie()
        verify(error, atLeastOnce()).onChanged(argErrorCaptor.capture())
        assertEquals(returnValue.error, argErrorCaptor.allValues.first())
        clearInvocations(useCase, error)
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
    }
}