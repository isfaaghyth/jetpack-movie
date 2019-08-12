package isfaaghyth.app.movie_details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import isfaaghyth.app.abstraction.util.ViewModelFactory
import isfaaghyth.app.abstraction.util.ViewModelKey
import isfaaghyth.app.movie_details.di.MovieDetailScope

@Module abstract class MovieDetailViewModelModule {

    @MovieDetailScope
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @MovieDetailScope
    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    internal abstract fun bindMovieViewModel(viewModel: MovieDetailViewModel): ViewModel

}