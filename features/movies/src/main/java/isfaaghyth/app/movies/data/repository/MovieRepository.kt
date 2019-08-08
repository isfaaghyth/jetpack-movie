package isfaaghyth.app.movies.data.repository

import isfaaghyth.app.data.Movies
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface MovieRepository {
    fun getPopularMovie(apiKey: String): Deferred<Response<Movies>>
}