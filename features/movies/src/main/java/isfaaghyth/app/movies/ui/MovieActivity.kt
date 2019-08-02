package isfaaghyth.app.movies.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.*
import isfaaghyth.app.abstraction.base.BaseActivity
import isfaaghyth.app.abstraction.util.toast
import isfaaghyth.app.movies.R
import isfaaghyth.app.movies.data.model.Movie
import isfaaghyth.app.movies.di.DaggerMovieComponent
import javax.inject.Inject

class MovieActivity: BaseActivity(), LifecycleOwner {

    override fun contentView(): Int = R.layout.activity_movie

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MovieViewModel

    override fun initView() {
        //view model provider
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(MovieViewModel::class.java)

        getMovie()
    }

    private fun getMovie() {
        viewModel.getPopularMovie()
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is MovieState.ShowLoading -> toast("loading")
                is MovieState.HideLoading -> toast("complete")
                is MovieState.LoadMovieSuccess -> {
                    for (movie: Movie in state.data.resultsIntent) {
                        Log.d("TAG", movie.title)
                    }
                }
                is MovieState.MovieError -> {
                    val errorBody = state.error.response().errorBody()
                    val errorResult = errorBody?.string()
                    onMessage(errorResult)
                }
            }
        })
    }

    override fun initInjector() {
        DaggerMovieComponent.builder()
            .movieModule(MovieModule())
            .build()
            .inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, MovieActivity::class.java)
        }
    }

}