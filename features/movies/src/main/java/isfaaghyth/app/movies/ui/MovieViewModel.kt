package isfaaghyth.app.movies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import isfaaghyth.app.abstraction.base.BaseViewModel
import isfaaghyth.app.abstraction.util.state.LoaderState
import isfaaghyth.app.abstraction.util.state.ResultState
import isfaaghyth.app.abstraction.util.thread.SchedulerProvider
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.movies.domain.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MovieContract {
    fun getPopularMovie()
}

class MovieViewModel @Inject constructor(
    private val useCase: MovieUseCase,
    dispatcher: SchedulerProvider
): BaseViewModel(dispatcher), MovieContract {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
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
        _state.value = LoaderState.ShowLoading
        launch(coroutineContext) {
            val result = useCase.getPopularMovie()
            withContext(Dispatchers.Main) {
                _state.value = LoaderState.HideLoading
                when (result) {
                    is ResultState.Success -> _result.postValue(result.data.resultsIntent)
                    is ResultState.Error -> _error.postValue(result.error)
                }
            }
        }
    }

}