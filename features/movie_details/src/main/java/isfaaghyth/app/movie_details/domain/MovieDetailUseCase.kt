package isfaaghyth.app.movie_details.domain

import isfaaghyth.app.abstraction.util.state.ResultState
import isfaaghyth.app.abstraction.util.UNSUCCESSFUL_MESSAGE
import isfaaghyth.app.abstraction.util.ext.fetchState
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.data.repository.movie_detail.MovieDetailRepository
import retrofit2.Response
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(val repository: MovieDetailRepository) {

    suspend fun getMovieDetail(movieId: String): ResultState<Movie> {
        return fetchState {
            wrapperDetail {
                repository.getMovieDetail(movieId)
            }
        }
    }

    suspend fun getTvDetail(movieId: String): ResultState<TVShow> {
        return fetchState {
            wrapperDetail {
                repository.getTVShowDetail(movieId)
            }
        }
    }

    private suspend fun <T: Any> wrapperDetail(call: suspend () -> Response<T>): ResultState<T> {
        return if (call.invoke().isSuccessful) {
            ResultState.Success(call.invoke().body()!!)
        } else {
            ResultState.Error(UNSUCCESSFUL_MESSAGE)
        }
    }

}