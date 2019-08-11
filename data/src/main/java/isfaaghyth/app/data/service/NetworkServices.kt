package isfaaghyth.app.data.service

import isfaaghyth.app.data.entity.Movies
import isfaaghyth.app.data.entity.TVShows
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkServices {

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String
    ): Deferred<Response<Movies>>

    @GET("tv/popular")
    fun getPopularTVShow(
        @Query("api_key") apiKey: String
    ): Deferred<Response<TVShows>>

}