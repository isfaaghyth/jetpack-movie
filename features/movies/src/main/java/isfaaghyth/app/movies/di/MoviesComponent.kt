package isfaaghyth.app.movies.di

import dagger.Component
import isfaaghyth.app.movies.ui.MovieActivity
import isfaaghyth.app.movies.ui.MovieModule

@MoviesScope
@Component(modules = [MovieModule::class])
interface MoviesComponent {
    fun inject(activity: MovieActivity)
}