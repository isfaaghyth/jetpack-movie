package isfaaghyth.app.movie_details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import isfaaghyth.app.abstraction.base.BaseViewModel
import isfaaghyth.app.abstraction.util.ResultState
import isfaaghyth.app.abstraction.util.thread.SchedulerProvider
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.movie_details.domain.MovieDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MoviewDetailContract {
    fun getMoviewDetail(movieId: String)
    fun getTVShowDetail(movieId: String)
}

class MovieDetailViewModel @Inject constructor(
    private val useCase: MovieDetailUseCase,
    dispatcher: SchedulerProvider
): BaseViewModel(dispatcher), MoviewDetailContract {

    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetal: LiveData<Movie>
        get() = _movieDetail

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    override fun getMoviewDetail(movieId: String) {
        launch {
            val result = useCase.testGetMovieDetail(movieId)
            withContext(Dispatchers.Main) {
                when (result) {
                    is ResultState.Success -> {
                        _movieDetail.postValue(result.data)
                    }
                    is ResultState.Error -> {
                        _error.postValue(result.error)
                    }
                }
            }
        }
    }

    override fun getTVShowDetail(movieId: String) {

    }

}