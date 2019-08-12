package isfaaghyth.app.movies.domain

import isfaaghyth.app.abstraction.util.ResultState
import isfaaghyth.app.abstraction.util.fetchState
import isfaaghyth.app.data.entity.Movies
import isfaaghyth.app.data.repository.movie.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend fun getPopularMovie(): ResultState<Movies> {
        return fetchState {
            val response = repository.getPopularMovie()
            if (response.isSuccessful) {
                ResultState.Success(response.body()!!)
            } else {
                ResultState.Error("Something problem")
            }
        }
    }

}