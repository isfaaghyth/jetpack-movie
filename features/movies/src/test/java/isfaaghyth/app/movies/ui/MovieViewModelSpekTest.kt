package isfaaghyth.app.movies.ui

import androidx.lifecycle.Observer
import isfaaghyth.app.abstraction.util.state.ResultState
import isfaaghyth.app.abstraction.util.thread.TestSchedulerProvider
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.Movies
import isfaaghyth.app.movies.InstantTaskExecutorRuleSpek
import isfaaghyth.app.movies.domain.MovieUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

@RunWith(JUnitPlatform::class)
class MovieViewModelSpekTest: Spek({

    InstantTaskExecutorRuleSpek(this)

    Feature("MovieViewModel") {
        val useCase: MovieUseCase = mock()
        val result: Observer<List<Movie>> = mock()
        val argResultCaptor: ArgumentCaptor<List<Movie>> = mock()

        val movies = listOf(
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

        val moviesData = Movies(movies)
        val schedulerProvider = TestSchedulerProvider()
        lateinit var viewModel: MovieViewModel

        Scenario("get movies") {
            val returnValue = ResultState.Success(moviesData)
            Given("viewModel have prepared") {
                viewModel = MovieViewModel(useCase, schedulerProvider)
                viewModel.result.observeForever(result)
            }

            When("getting popular movies") {
                runBlocking {
                    Mockito.`when`(useCase.getPopularMovie()).thenReturn(returnValue)
                    viewModel.getPopularMovie()
                }
            }

            Then("it should return correct data") {
                verify(result, Mockito.atLeastOnce()).onChanged(argResultCaptor.capture())
                assertEquals(returnValue.data.resultsIntent, argResultCaptor.allValues.first())
            }
        }
    }

})

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)