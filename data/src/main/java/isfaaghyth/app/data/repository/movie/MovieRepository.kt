package isfaaghyth.app.data.repository.movie

import isfaaghyth.app.data.entity.Movies
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface MovieRepository {
    suspend fun getPopularMovie(): Response<Movies>
}