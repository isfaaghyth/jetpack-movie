package isfaaghyth.app.data.repository.tvshow

import isfaaghyth.app.data.BuildConfig
import isfaaghyth.app.data.entity.TVShows
import isfaaghyth.app.data.service.NetworkServices
import retrofit2.Response
import javax.inject.Inject

class TVShowRepositoryImpl @Inject constructor(
    private val service: NetworkServices
): TVShowRepository {

    override suspend fun getPopularTVShow(): Response<TVShows> {
        return service.getPopularTVShow(API_KEY)
    }

    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

}