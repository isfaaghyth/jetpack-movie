package isfaaghyth.app.data.repository.movie_detail

import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.TVShow
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface MovieDetailRepository {
    fun getMovieDetail(movieId: String): Response<Movie>
    fun getTVShowDetail(movieId: String): Response<TVShow>

    suspend fun testGetMovieDetail(movieId: String): Response<Movie>
}