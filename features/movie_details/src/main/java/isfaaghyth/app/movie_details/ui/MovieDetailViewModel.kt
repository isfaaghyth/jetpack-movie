package isfaaghyth.app.movie_details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import isfaaghyth.app.abstraction.base.BaseViewModel
import isfaaghyth.app.abstraction.util.state.LoaderState
import isfaaghyth.app.abstraction.util.state.ResultState
import isfaaghyth.app.abstraction.util.thread.SchedulerProvider
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.TVShow
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
    val movieDetail: LiveData<Movie>
        get() = _movieDetail

    private val _tvDetail = MutableLiveData<TVShow>()
    val tvDetail: LiveData<TVShow>
        get() = _tvDetail

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    init {
        getMoviewDetail("123")
        getTVShowDetail("123")
    }

    override fun getMoviewDetail(movieId: String) {
        _state.value = LoaderState.ShowLoading
        launch {
            val result = useCase.getMovieDetail(movieId)
            withContext(Dispatchers.Main) {
                _state.value = LoaderState.HideLoading
                when (result) {
                    is ResultState.Success -> _movieDetail.postValue(result.data)
                    is ResultState.Error -> _error.postValue(result.error)
                }
            }
        }
    }

    override fun getTVShowDetail(movieId: String) {
        _state.value = LoaderState.ShowLoading
        launch {
            val result = useCase.getTvDetail(movieId)
            withContext(Dispatchers.Main) {
                _state.value = LoaderState.HideLoading
                when (result) {
                    is ResultState.Success -> _tvDetail.postValue(result.data)
                    is ResultState.Error -> _error.postValue(result.error)
                }
            }
        }
    }

}