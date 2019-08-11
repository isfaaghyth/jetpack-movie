package isfaaghyth.app.tvshows.ui

import dagger.Module
import dagger.Provides
import isfaaghyth.app.abstraction.util.thread.ApplicationSchedulerProvider
import isfaaghyth.app.abstraction.util.thread.SchedulerProvider
import isfaaghyth.app.data.di.DataModule
import isfaaghyth.app.data.di.DataScope
import isfaaghyth.app.data.repository.tvshow.TVShowRepository
import isfaaghyth.app.data.repository.tvshow.TVShowRepositoryImpl
import isfaaghyth.app.data.service.NetworkServices
import isfaaghyth.app.tvshows.di.TVShowScope
import isfaaghyth.app.tvshows.domain.TVShowUseCase

@Module(includes = [DataModule::class])
class TVShowModule {

    @TVShowScope @Provides
    fun provideRepository(@DataScope service: NetworkServices): TVShowRepository {
        return TVShowRepositoryImpl(service)
    }

    @TVShowScope @Provides
    fun provideUseCase(repository: TVShowRepository): TVShowUseCase {
        return TVShowUseCase(repository)
    }

    @TVShowScope @Provides
    fun provideSchedulerProvider(): SchedulerProvider = ApplicationSchedulerProvider()

}