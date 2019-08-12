package isfaaghyth.app.data.repository.movie_detail

import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.data.service.NetworkServices
import retrofit2.Response
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val service: NetworkServices
): MovieDetailRepository {

    override suspend fun testGetMovieDetail(movieId: String): Response<Movie> {
        return service.getMovieDetail(movieId)
    }

    override fun getMovieDetail(movieId: String): Response<Movie> {
        return service.getDetail(MOVIE_PATH, movieId)
    }

    override fun getTVShowDetail(movieId: String): Response<TVShow> {
        return service.getDetail(TV_PATH, movieId)
    }

    companion object {
        const val MOVIE_PATH = "movie"
        const val TV_PATH = "tv"
    }

}