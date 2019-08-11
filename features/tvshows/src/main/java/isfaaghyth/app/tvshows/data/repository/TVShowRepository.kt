package isfaaghyth.app.tvshows.data.repository

import isfaaghyth.app.data.TVShows
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface TVShowRepository {
    fun getPopularTVShow(apiKey: String): Deferred<Response<TVShows>>
}