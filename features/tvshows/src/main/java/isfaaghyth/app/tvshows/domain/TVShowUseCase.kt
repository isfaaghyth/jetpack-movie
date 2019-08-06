package isfaaghyth.app.tvshows.domain

import isfaaghyth.app.data.BuildConfig
import isfaaghyth.app.data.TVShows
import isfaaghyth.app.tvshows.data.repository.TVShowRepository
import javax.inject.Inject

class TVShowUseCase @Inject constructor(private val repository: TVShowRepository) {

    suspend fun getPopularTvShow(): TVShows {
        return repository.getPopularTVShow(BuildConfig.API_KEY).await()
    }

}