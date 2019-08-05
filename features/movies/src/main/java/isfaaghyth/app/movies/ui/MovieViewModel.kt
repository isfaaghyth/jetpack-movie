package isfaaghyth.app.movies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import isfaaghyth.app.abstraction.base.BaseViewModel
import isfaaghyth.app.movies.domain.MovieUseCase
import kotlinx.coroutines.*
import retrofit2.HttpException
import javax.inject.Inject

interface MovieContract {
    fun getPopularMovie()
}

class MovieViewModel @Inject constructor(
    private val useCase: MovieUseCase,
    dispatcher: CoroutineDispatcher
): BaseViewModel(dispatcher), MovieContract {

    private val _state = MutableLiveData<MovieState>()

    val state: LiveData<MovieState>
        get() = _state

    override fun getPopularMovie() {
        _state.value = MovieState.ShowLoading
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = useCase.getPopularMovie()
                withContext(Dispatchers.Main) {
                    _state.value = MovieState.HideLoading
                    _state.postValue(MovieState.LoadMovieSuccess(result))
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    _state.value = MovieState.MovieError(e)
                }
            }
        }
    }

}