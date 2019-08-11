package isfaaghyth.app.tvshows.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import isfaaghyth.app.abstraction.base.BaseViewModel
import isfaaghyth.app.abstraction.util.thread.SchedulerProvider
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.tvshows.domain.TVShowUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

interface TVShowContract {
    fun getPopularTvShow()
}

class TVShowViewModel @Inject constructor(
    private val useCase: TVShowUseCase,
    dispatcher: SchedulerProvider
): BaseViewModel(dispatcher), TVShowContract {

    private val _state = MutableLiveData<TVShowState>()
    val state: LiveData<TVShowState>
        get() = _state

    private val _result = MutableLiveData<List<TVShow>>()
    val result: LiveData<List<TVShow>>
        get() = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    init {
        getPopularTvShow()
    }

    override fun getPopularTvShow() {
        _state.value = TVShowState.ShowLoading
        launch {
            val result = useCase.getPopularTvShow()
            withContext(Dispatchers.Main) {
                _state.value = TVShowState.HideLoading
                when (result) {
                    is TVShowState.LoadSuccess -> _result.postValue(result.data.resultsIntent)
                    is TVShowState.MovieError -> _error.postValue(result.error.message)
                }
            }
        }
    }

}