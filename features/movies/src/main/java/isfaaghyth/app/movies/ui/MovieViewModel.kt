package isfaaghyth.app.movies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import isfaaghyth.app.abstraction.base.BaseViewModel
import isfaaghyth.app.abstraction.util.thread.SchedulerProvider
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.movies.domain.MovieUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

interface MovieContract {
    fun getPopularMovie()
}

class MovieViewModel @Inject constructor(
    private val useCase: MovieUseCase,
    dispatcher: SchedulerProvider
): BaseViewModel(dispatcher), MovieContract {

    private val _state = MutableLiveData<MovieState>()
    val state: LiveData<MovieState>
        get() = _state

    private val _result = MutableLiveData<List<Movie>>()
    val result: LiveData<List<Movie>>
        get() = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    init {
        getPopularMovie()
    }

    override fun getPopularMovie() {
        _state.value = MovieState.ShowLoading
        launch(coroutineContext) {
            val result = useCase.getPopularMovie()
            withContext(Dispatchers.Main) {
                _state.value = MovieState.HideLoading
                when (result) {
                    is MovieState.LoadSuccess -> _result.postValue(result.data.resultsIntent)
                    is MovieState.MovieError -> _error.postValue(result.error.message)
                }
            }
        }
    }

}