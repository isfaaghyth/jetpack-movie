package isfaaghyth.app.tvshows.di

import dagger.Component
import isfaaghyth.app.tvshows.ui.TVShowFragment
import isfaaghyth.app.tvshows.ui.TVShowModule
import isfaaghyth.app.tvshows.ui.TVShowViewModelModule

@TVShowScope
@Component(modules = [
    TVShowModule::class,
    TVShowViewModelModule::class
])
interface TVShowComponent {
    fun inject(activity: TVShowFragment)
}