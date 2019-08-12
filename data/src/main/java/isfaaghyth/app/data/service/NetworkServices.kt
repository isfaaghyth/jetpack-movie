package isfaaghyth.app.data.service

import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.Movies
import isfaaghyth.app.data.entity.TVShows
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkServices {

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String
    ): Response<Movies>

    @GET("tv/popular")
    suspend fun getPopularTVShow(
        @Query("api_key") apiKey: String
    ): Response<TVShows>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): Response<Movie>

    @GET("{type}/{movie_id}")
    fun <T> getDetail(
        @Path("type") type: String,
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): Response<T>

}