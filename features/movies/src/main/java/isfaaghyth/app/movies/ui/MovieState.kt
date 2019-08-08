package isfaaghyth.app.movies.ui

import isfaaghyth.app.data.Movies

sealed class MovieState {
    object ShowLoading: MovieState()
    object HideLoading: MovieState()

    data class MovieError(val error: Exception): MovieState()
    data class LoadSuccess(val data: Movies): MovieState()
}