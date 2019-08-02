package isfaaghyth.app.movies.ui

import isfaaghyth.app.movies.data.model.Movies
import retrofit2.HttpException

sealed class MovieState {
    object ShowLoading: MovieState()
    object HideLoading: MovieState()

    data class MovieError(val error: HttpException): MovieState()
    data class LoadMovieSuccess(val data: Movies): MovieState()
}