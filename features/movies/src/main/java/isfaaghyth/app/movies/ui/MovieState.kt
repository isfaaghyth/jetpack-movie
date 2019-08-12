package isfaaghyth.app.movies.ui

sealed class MovieState {
    object ShowLoading: MovieState()
    object HideLoading: MovieState()
}