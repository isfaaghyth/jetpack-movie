package isfaaghyth.app.data.repository.movie

import isfaaghyth.app.data.entity.Movies
import isfaaghyth.app.data.service.NetworkServices
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: NetworkServices
): MovieRepository {

    override fun getPopularMovie(apiKey: String): Deferred<Response<Movies>> {
        return service.getPopularMovie(apiKey)
    }

}