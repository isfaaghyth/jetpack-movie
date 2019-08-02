package isfaaghyth.app.movies.ui

import dagger.Module
import dagger.Provides
import isfaaghyth.app.movies.data.MovieService
import isfaaghyth.app.movies.data.repository.MovieRepository
import isfaaghyth.app.movies.data.repository.MovieRepositoryImpl
import isfaaghyth.app.movies.di.MovieScope
import isfaaghyth.app.movies.domain.MovieUseCase
import isfaaghyth.app.network.Network.retrofitClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module class MovieModule {

    @MovieScope @Provides
    fun provideServices(): MovieService {
        return retrofitClient().create(MovieService::class.java)
    }

    @MovieScope @Provides
    fun provideRepository(service: MovieService): MovieRepository {
        return MovieRepositoryImpl(service)
    }

    @MovieScope @Provides
    fun provideUseCase(repository: MovieRepository): MovieUseCase {
        return MovieUseCase(repository)
    }

    @MovieScope @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

}