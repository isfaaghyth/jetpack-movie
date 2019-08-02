package isfaaghyth.app.movies.domain

import isfaaghyth.app.movies.BuildConfig
import isfaaghyth.app.movies.data.model.Movies
import isfaaghyth.app.movies.data.repository.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend fun getPopularMovie(apiKey: String = BuildConfig.API_KEY): Movies {
        return repository.getPopularMovie(apiKey).await()
    }

}