package isfaaghyth.app.tvshows.data.repository

import isfaaghyth.app.data.TVShows
import isfaaghyth.app.tvshows.data.TVShowService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class TVShowRepositoryImpl @Inject constructor(private val service: TVShowService): TVShowRepository {

    override fun getPopularTVShow(apiKey: String): Deferred<TVShows> {
        return service.getPopularTVShow(apiKey)
    }

}