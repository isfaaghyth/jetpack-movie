package isfaaghyth.app.movies.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import isfaaghyth.app.abstraction.util.state.ResultState
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

@ExperimentalCoroutinesApi
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
        /* given */
        val returnValue = ResultState.Success(moviesData)

        /* when */
        `when`(useCase.getPopularMovie()).thenReturn(returnValue)

        /* do */
        viewModel.getPopularMovie()

        /* verify */
        verify(result, atLeastOnce()).onChanged(argResultCaptor.capture())

        /* then */
        Assert.assertEquals(returnValue.data.resultsIntent, argResultCaptor.allValues.first())

        /* clear */
        clearInvocations(useCase, result)
    }

    @Test fun `should return an error without api key`() = runBlocking {
        /* given */
        val returnValue = ResultState.Error("API Key Not Found")

        /* when */
        `when`(useCase.getPopularMovie()).thenReturn(returnValue)

        /* do */
        viewModel.getPopularMovie()

        /* verify and then */
        verify(error, atLeastOnce()).onChanged(argErrorCaptor.capture())

        Assert.assertEquals(returnValue.error, argErrorCaptor.allValues.first())

        /* clear */
        clearInvocations(useCase, error)
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
    }
}