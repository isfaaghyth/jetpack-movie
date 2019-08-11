package isfaaghyth.app.data.repository.tvshow

import isfaaghyth.app.data.entity.TVShows
import isfaaghyth.app.data.service.NetworkServices
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject

class TVShowRepositoryImpl @Inject constructor(
    private val service: NetworkServices
): TVShowRepository {

    override fun getPopularTVShow(apiKey: String): Deferred<Response<TVShows>> {
        return service.getPopularTVShow(apiKey)
    }

}