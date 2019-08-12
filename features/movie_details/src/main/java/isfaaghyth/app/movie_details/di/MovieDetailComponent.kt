package isfaaghyth.app.movie_details.di

import dagger.Component
import isfaaghyth.app.movie_details.ui.MovieDetailActivity
import isfaaghyth.app.movie_details.ui.MovieDetailModule
import isfaaghyth.app.movie_details.ui.MovieDetailViewModelModule

@MovieDetailScope
@Component(modules = [
    MovieDetailModule::class,
    MovieDetailViewModelModule::class
])
interface MovieDetailComponent {
    fun inject(activity: MovieDetailActivity)
}