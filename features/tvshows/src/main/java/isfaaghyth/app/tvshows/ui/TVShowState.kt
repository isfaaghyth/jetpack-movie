package isfaaghyth.app.tvshows.ui

sealed class TVShowState {
    object ShowLoading: TVShowState()
    object HideLoading: TVShowState()
}