package isfaaghyth.app.tvshows.ui

import isfaaghyth.app.data.TVShows
import retrofit2.HttpException

sealed class TVShowState {
    object ShowLoading: TVShowState()
    object HideLoading: TVShowState()

    data class MovieError(val error: HttpException): TVShowState()
    data class LoadSuccess(val data: TVShows): TVShowState()
}