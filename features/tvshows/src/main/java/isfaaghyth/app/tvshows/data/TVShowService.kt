package isfaaghyth.app.tvshows.data

import isfaaghyth.app.data.TVShows
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowService {

    @GET("tv/popular")
    fun getPopularTVShow(
        @Query("api_key") apiKey: String
    ): Deferred<Response<TVShows>>

}