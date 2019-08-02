package isfaaghyth.app.movies.ui

import isfaaghyth.app.movies.data.model.Movies

sealed class MovieState {
    object ShowLoading: MovieState()
    object HideLoading: MovieState()

    data class LoadMovieSuccess(val data: Movies): MovieState()
}