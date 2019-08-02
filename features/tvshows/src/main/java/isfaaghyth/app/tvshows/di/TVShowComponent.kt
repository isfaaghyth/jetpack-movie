package isfaaghyth.app.tvshows.di

import dagger.Component
import isfaaghyth.app.tvshows.ui.TVShowActivity
import isfaaghyth.app.tvshows.ui.TVShowModule

@TVShowScope
@Component(modules = [TVShowModule::class])
interface TVShowComponent {
    fun inject(activity: TVShowActivity)
}