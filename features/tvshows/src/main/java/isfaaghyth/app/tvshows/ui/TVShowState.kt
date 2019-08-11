package isfaaghyth.app.tvshows.ui

import isfaaghyth.app.data.TVShows

sealed class TVShowState {
    object ShowLoading: TVShowState()
    object HideLoading: TVShowState()

    data class MovieError(val error: Exception): TVShowState()
    data class LoadSuccess(val data: TVShows): TVShowState()
}