package isfaaghyth.app.data.service

import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.Movies
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.data.entity.TVShows
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkServices {

    @GET("movie/popular")
    suspend fun getPopularMovie(): Response<Movies>

    @GET("tv/popular")
    suspend fun getPopularTVShow(): Response<TVShows>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String
    ): Response<Movie>

    @GET("tv/{movie_id}")
    suspend fun getTvDetail(
        @Path("movie_id") movieId: String
    ): Response<TVShow>

}