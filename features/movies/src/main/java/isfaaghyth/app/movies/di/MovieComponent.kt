package isfaaghyth.app.movies.di

import dagger.Component
import isfaaghyth.app.movies.ui.MovieFragment
import isfaaghyth.app.movies.ui.MovieModule
import isfaaghyth.app.movies.ui.MovieViewModelModule

@MovieScope
@Component(modules = [
    MovieModule::class,
    MovieViewModelModule::class
])
interface MovieComponent {
    fun inject(fragment: MovieFragment)
}