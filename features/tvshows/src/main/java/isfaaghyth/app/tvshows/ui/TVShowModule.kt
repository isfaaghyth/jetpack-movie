package isfaaghyth.app.tvshows.ui

import dagger.Module
import dagger.Provides
import isfaaghyth.app.abstraction.util.thread.ApplicationSchedulerProvider
import isfaaghyth.app.abstraction.util.thread.SchedulerProvider
import isfaaghyth.app.network.Network
import isfaaghyth.app.tvshows.data.TVShowService
import isfaaghyth.app.tvshows.data.repository.TVShowRepository
import isfaaghyth.app.tvshows.data.repository.TVShowRepositoryImpl
import isfaaghyth.app.tvshows.di.TVShowScope
import isfaaghyth.app.tvshows.domain.TVShowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module class TVShowModule {

    @TVShowScope @Provides
    fun provideServices(): TVShowService {
        return Network.retrofitClient().create(TVShowService::class.java)
    }

    @TVShowScope @Provides
    fun provideRepository(service: TVShowService): TVShowRepository {
        return TVShowRepositoryImpl(service)
    }

    @TVShowScope @Provides
    fun provideUseCase(repository: TVShowRepository): TVShowUseCase {
        return TVShowUseCase(repository)
    }

    @TVShowScope @Provides
    fun provideSchedulerProvider(): SchedulerProvider = ApplicationSchedulerProvider()

}