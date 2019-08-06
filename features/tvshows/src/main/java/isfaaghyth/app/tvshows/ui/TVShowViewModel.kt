package isfaaghyth.app.tvshows.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import isfaaghyth.app.abstraction.base.BaseViewModel
import isfaaghyth.app.tvshows.domain.TVShowUseCase
import kotlinx.coroutines.*
import retrofit2.HttpException
import javax.inject.Inject

interface TVShowContract {
    fun getPopularTVShow()
}

class TVShowViewModel @Inject constructor(
    private val useCase: TVShowUseCase,
    dispatcher: CoroutineDispatcher
): BaseViewModel(dispatcher), TVShowContract {

    private val _state = MutableLiveData<TVShowState>()
    val state: LiveData<TVShowState>
        get() = _state

    init {
        getPopularTVShow()
    }

    override fun getPopularTVShow() {
        _state.value = TVShowState.ShowLoading
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = useCase.getPopularTvShow()
                withContext(Dispatchers.Main) {
                    _state.value = TVShowState.HideLoading
                    _state.postValue(TVShowState.LoadSuccess(result))
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    _state.value = TVShowState.HideLoading
                    _state.postValue(TVShowState.MovieError(e))
                }
            }
        }
    }

}