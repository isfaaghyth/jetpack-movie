package isfaaghyth.app.movies.data.repository

import isfaaghyth.app.movies.data.MovieService
import isfaaghyth.app.data.Movies
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val service: MovieService): MovieRepository {

    override fun getPopularMovie(apiKey: String): Deferred<Movies> {
        return service.getPopularMovie(apiKey)
    }

}