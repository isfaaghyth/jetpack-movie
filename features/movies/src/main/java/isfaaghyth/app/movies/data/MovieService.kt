package isfaaghyth.app.movies.data

import isfaaghyth.app.movies.data.model.Movies
import isfaaghyth.app.movies.data.model.TvShows
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String
    ): Deferred<Movies>

    @GET("tv/popular")
    fun getTVShow(
        @Query("api_key") apiKey: String
    ): Deferred<TvShows>

}