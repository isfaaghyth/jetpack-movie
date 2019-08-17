package isfaaghyth.app.movie_details.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import isfaaghyth.app.abstraction.util.state.ResultState
import isfaaghyth.app.abstraction.util.thread.TestSchedulerProvider
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.movie_details.domain.MovieDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MovieDetailViewModelTest {

    @get:Rule val instantExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var usecase: MovieDetailUseCase
    private lateinit var viewModel: MovieDetailViewModel

    @Mock lateinit var movieObserver: Observer<Movie>
    @Mock lateinit var tvshowObserver: Observer<TVShow>
    @Mock lateinit var errorObserver: Observer<String>

    @Captor lateinit var movieCaptor: ArgumentCaptor<Movie>
    @Captor lateinit var tvshowCaptor: ArgumentCaptor<TVShow>
    @Captor lateinit var errorCaptor: ArgumentCaptor<String>

    private val schedulerProvider = TestSchedulerProvider()

    @Before fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(schedulerProvider.ui())
        viewModel = MovieDetailViewModel(usecase, schedulerProvider)
        viewModel.movieDetail.observeForever(movieObserver)
        viewModel.tvDetail.observeForever(tvshowObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @Test fun `show return movie detail`() = runBlocking {
        val movieId = "123"
        val returnValue = ResultState.Success(movie)
        `when`(usecase.getMovieDetail(movieId)).thenReturn(returnValue)
        viewModel.getMovieDetail(movieId)
        verify(movieObserver, atLeastOnce()).onChanged(movieCaptor.capture())
        assert(returnValue.data == movieCaptor.allValues.first())
    }

    @Test fun `should movie detail return error`() = runBlocking {
        val movieId = "123"
        val returnValue = ResultState.Error("something error!")
        `when`(usecase.getMovieDetail(movieId)).thenReturn(returnValue)
        viewModel.getMovieDetail(movieId)
        verify(errorObserver, atLeastOnce()).onChanged(errorCaptor.capture())
        assert(returnValue.error == errorCaptor.allValues.first())
    }


    @Test fun `show return tv detail`() = runBlocking {
        val movieId = "123"
        val returnValue = ResultState.Success(tvshow)
        `when`(usecase.getTvDetail(movieId)).thenReturn(returnValue)
        viewModel.getTVShowDetail(movieId)
        verify(tvshowObserver, atLeastOnce()).onChanged(tvshowCaptor.capture())
        assert(returnValue.data == tvshowCaptor.allValues.first())
    }

    @Test fun `should tv detail return error`() = runBlocking {
        val movieId = "123"
        val returnValue = ResultState.Error("something error!")
        `when`(usecase.getTvDetail(movieId)).thenReturn(returnValue)
        viewModel.getTVShowDetail(movieId)
        verify(errorObserver, atLeastOnce()).onChanged(errorCaptor.capture())
        assert(returnValue.error == errorCaptor.allValues.first())
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
        clearInvocations(usecase, movieObserver, errorObserver)
    }

    companion object {

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

    }

}