package isfaaghyth.app.tvshows.domain

import isfaaghyth.app.abstraction.util.ResultState
import isfaaghyth.app.abstraction.util.fetchState
import isfaaghyth.app.data.BuildConfig
import isfaaghyth.app.data.entity.TVShows
import isfaaghyth.app.data.repository.tvshow.TVShowRepository
import isfaaghyth.app.tvshows.ui.TVShowState
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException
import javax.inject.Inject

class TVShowUseCase @Inject constructor(private val repository: TVShowRepository) {

    suspend fun getPopularTvShow(): ResultState<TVShows> {
        return fetchState {
            val response = repository.getPopularTVShow()
            if (response.isSuccessful) {
                ResultState.Success(response.body()!!)
            } else {
                ResultState.Error("Something problem")
            }
        }
    }

}