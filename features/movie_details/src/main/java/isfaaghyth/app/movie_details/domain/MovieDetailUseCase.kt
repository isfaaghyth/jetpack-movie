package isfaaghyth.app.movie_details.domain

import isfaaghyth.app.abstraction.util.ResultState
import isfaaghyth.app.abstraction.util.fetchState
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.repository.movie_detail.MovieDetailRepository
import java.io.IOException
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(val repository: MovieDetailRepository) {

    suspend fun testGetMovieDetail(movieId: String): ResultState<Movie> {
        return fetchState {
            val response = repository.testGetMovieDetail(movieId)
            if (response.isSuccessful) {
                ResultState.Success(response.body()!!)
            } else {
                ResultState.Error("Something problem")
            }
        }
    }

}