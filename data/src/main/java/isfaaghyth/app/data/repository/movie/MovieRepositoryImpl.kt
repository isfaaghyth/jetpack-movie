package isfaaghyth.app.data.repository.movie

import isfaaghyth.app.data.BuildConfig
import isfaaghyth.app.data.entity.Movies
import isfaaghyth.app.data.service.NetworkServices
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: NetworkServices
): MovieRepository {

    override suspend fun getPopularMovie(): Response<Movies> {
        return service.getPopularMovie(API_KEY)
    }

    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

}