package isfaaghyth.app.tvshows.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import isfaaghyth.app.abstraction.util.ViewModelFactory
import isfaaghyth.app.abstraction.util.ViewModelKey
import isfaaghyth.app.tvshows.di.TVShowScope

@Module abstract class TVShowViewModelModule {

    @TVShowScope
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TVShowViewModel::class)
    internal abstract fun bindMovieViewModel(viewModel: TVShowViewModel): ViewModel

}