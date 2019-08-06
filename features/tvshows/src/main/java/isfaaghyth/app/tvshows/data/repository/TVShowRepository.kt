package isfaaghyth.app.tvshows.data.repository

import isfaaghyth.app.data.TVShows
import kotlinx.coroutines.Deferred

interface TVShowRepository {
    fun getPopularTVShow(apiKey: String): Deferred<TVShows>
}