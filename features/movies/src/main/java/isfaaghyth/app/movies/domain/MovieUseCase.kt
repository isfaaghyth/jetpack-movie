package isfaaghyth.app.movies.domain

import isfaaghyth.app.movies.BuildConfig
import isfaaghyth.app.data.repository.movie.MovieRepository
import isfaaghyth.app.movies.ui.MovieState
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend fun getPopularMovie(apiKey: String = API_KEY): MovieState {
        val response = repository.getPopularMovie(apiKey).await()
        return try {
            if (response.isSuccessful) {
                response.body()?.let {
                    MovieState.LoadSuccess(it)
                }?: MovieState.MovieError(IOException("Result Not Found"))
            } else {
                MovieState.MovieError(IOException("Error occurred during fetching movies!"))
            }
        } catch (e: HttpException) {
            MovieState.MovieError(IOException(e.message()))
        } catch (e: Throwable) {
            MovieState.MovieError(IOException(e.message))
        }
    }

    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

}