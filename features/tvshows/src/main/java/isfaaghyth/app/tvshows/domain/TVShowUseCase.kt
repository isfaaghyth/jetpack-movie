package isfaaghyth.app.tvshows.domain

import isfaaghyth.app.abstraction.util.state.ResultState
import isfaaghyth.app.abstraction.util.UNSUCCESSFUL_MESSAGE
import isfaaghyth.app.abstraction.util.ext.fetchState
import isfaaghyth.app.data.entity.TVShows
import isfaaghyth.app.data.repository.tvshow.TVShowRepository
import javax.inject.Inject

class TVShowUseCase @Inject constructor(private val repository: TVShowRepository) {

    suspend fun getPopularTvShow(): ResultState<TVShows> {
        return fetchState {
            val response = repository.getPopularTVShow()
            if (response.isSuccessful) {
                ResultState.Success(response.body()!!)
            } else {
                ResultState.Error(UNSUCCESSFUL_MESSAGE)
            }
        }
    }

}