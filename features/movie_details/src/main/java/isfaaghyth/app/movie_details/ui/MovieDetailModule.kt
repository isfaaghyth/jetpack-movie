package isfaaghyth.app.movie_details.ui

import dagger.Module
import dagger.Provides
import isfaaghyth.app.abstraction.util.thread.ApplicationSchedulerProvider
import isfaaghyth.app.abstraction.util.thread.SchedulerProvider
import isfaaghyth.app.data.di.DataModule
import isfaaghyth.app.data.di.DataScope
import isfaaghyth.app.data.repository.movie_detail.MovieDetailRepository
import isfaaghyth.app.data.repository.movie_detail.MovieDetailRepositoryImpl
import isfaaghyth.app.data.routes.NetworkServices
import isfaaghyth.app.movie_details.di.MovieDetailScope
import isfaaghyth.app.movie_details.domain.MovieDetailUseCase

@Module(includes = [DataModule::class])
class MovieDetailModule {

    @MovieDetailScope @Provides
    fun provideRepository(@DataScope service: NetworkServices): MovieDetailRepository {
        return MovieDetailRepositoryImpl(service)
    }

    @MovieDetailScope @Provides
    fun provideUseCase(repository: MovieDetailRepository): MovieDetailUseCase {
        return MovieDetailUseCase(repository)
    }

    @MovieDetailScope @Provides
    fun provideSchedulerProvider(): SchedulerProvider = ApplicationSchedulerProvider()

}