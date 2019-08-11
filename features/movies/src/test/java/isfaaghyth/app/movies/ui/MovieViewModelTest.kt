package isfaaghyth.app.movies.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import isfaaghyth.app.abstraction.util.thread.TestSchedulerProvider
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.Movies
import isfaaghyth.app.movies.domain.MovieUseCase
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
import java.io.IOException

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule val instantExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var result: Observer<List<Movie>>
    @Mock lateinit var state: Observer<MovieState>
    @Mock lateinit var error: Observer<String>

    @Captor lateinit var argResultCaptor: ArgumentCaptor<List<Movie>>
    @Captor lateinit var argStateCaptor: ArgumentCaptor<MovieState>

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
        viewModel.state.observeForever(state)
        viewModel.error.observeForever(error)
    }

    @Test fun `should return a response of movies data`() = runBlocking {
        /* given */
        val returnValue = MovieState.LoadSuccess(moviesData)

        /* when */
        `when`(useCase.getPopularMovie()).thenReturn(returnValue)

        /* do */
        viewModel.getPopularMovie()

        /* verify */
        verify(result, atLeastOnce()).onChanged(argResultCaptor.capture())
        verify(state, atLeastOnce()).onChanged(argStateCaptor.capture())

        /* then */
        Assert.assertEquals(MovieState.HideLoading, argStateCaptor.allValues.first())
        Assert.assertEquals(returnValue.data.resultsIntent, argResultCaptor.allValues.first())

        /* clear */
        clearInvocations(useCase, result, state)
    }

    @Test fun `should return an error without api key`() = runBlocking {
        /* given */
        val returnValue = MovieState.MovieError(IOException("API Key Not Found"))

        /* when */
        `when`(useCase.getPopularMovie("")).thenReturn(returnValue)

        /* do */
        viewModel.getPopularMovie()

        /* verify and then */
        verifyNoMoreInteractions(error)

        /* clear */
        clearInvocations(useCase, error)
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
    }
}