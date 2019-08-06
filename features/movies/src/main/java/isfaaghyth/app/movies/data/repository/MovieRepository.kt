package isfaaghyth.app.movies.data.repository

import isfaaghyth.app.data.Movies
import kotlinx.coroutines.Deferred

interface MovieRepository {
    fun getPopularMovie(apiKey: String): Deferred<Movies>
}