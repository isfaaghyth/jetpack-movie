package isfaaghyth.app.movies.data

import isfaaghyth.app.data.Movies
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String
    ): Deferred<Movies>

}