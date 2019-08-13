package isfaaghyth.app.tvshows.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import isfaaghyth.app.abstraction.util.state.LoaderState
import isfaaghyth.app.abstraction.util.state.ResultState
import isfaaghyth.app.abstraction.util.thread.TestSchedulerProvider
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.data.entity.TVShows
import isfaaghyth.app.tvshows.domain.TVShowUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class TVShowViewModelTest {

    @get:Rule val instantExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var result: Observer<List<TVShow>>
    @Mock lateinit var state: Observer<LoaderState>
    @Mock lateinit var error: Observer<String>

    @Captor lateinit var argResultCaptor: ArgumentCaptor<List<TVShow>>
    @Captor lateinit var argStateCaptor: ArgumentCaptor<LoaderState>
    @Captor lateinit var argErrorCaptor: ArgumentCaptor<String>

    @Mock lateinit var useCase: TVShowUseCase

    private lateinit var viewModel: TVShowViewModel

    private val tvShow = listOf(
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

    private val moviesData = TVShows(tvShow)
    private val schedulerProvider = TestSchedulerProvider()

    @Before fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(schedulerProvider.ui())

        viewModel = TVShowViewModel(useCase, schedulerProvider)
        viewModel.result.observeForever(result)
        viewModel.state.observeForever(state)
        viewModel.error.observeForever(error)
    }

    @Test fun `should return a response of movies data`() = runBlocking {
        val returnValue = ResultState.Success(moviesData)
        Mockito.`when`(useCase.getPopularTvShow()).thenReturn(returnValue)

        viewModel.getPopularTvShow()

        verify(result, atLeastOnce()).onChanged(argResultCaptor.capture())
        verify(state, atLeastOnce()).onChanged(argStateCaptor.capture())

        assertEquals(returnValue.data.resultsIntent, argResultCaptor.allValues.first())
        clearInvocations(useCase, result, state)
    }

    @Test fun `should return an error without api key`() = runBlocking {
        val returnValue = ResultState.Error("error")
        Mockito.`when`(useCase.getPopularTvShow()).thenReturn(returnValue)

        viewModel.getPopularTvShow()

        verify(error, atLeastOnce()).onChanged(argErrorCaptor.capture())

        assertEquals(returnValue.error, argErrorCaptor.allValues.first())
        clearInvocations(useCase, error)
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
    }

}