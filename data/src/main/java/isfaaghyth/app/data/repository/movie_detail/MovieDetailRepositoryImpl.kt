package isfaaghyth.app.data.repository.movie_detail

import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.data.routes.NetworkServices
import retrofit2.Response
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val service: NetworkServices
): MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: String): Response<Movie> {
        return service.getMovieDetail(movieId)
    }

    override suspend fun getTVShowDetail(movieId: String): Response<TVShow> {
        return service.getTvDetail(movieId)
    }

}