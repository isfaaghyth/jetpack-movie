package isfaaghyth.app.movies.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import isfaaghyth.app.abstraction.util.toast
import isfaaghyth.app.movies.R
import isfaaghyth.app.movies.data.model.Movie
import isfaaghyth.app.movies.data.model.Movies
import isfaaghyth.app.movies.di.DaggerMovieComponent
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieFragment: Fragment() {

    fun contentView(): Int = R.layout.fragment_movie

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(contentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInjector()
        initView()
    }

    fun initView() {
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
                is MovieState.LoadMovieSuccess -> movieResult(state.data)
                is MovieState.MovieError -> {
                    val errorBody = state.error.response().errorBody()
                    val errorResult = errorBody?.string()
                    toast(errorResult)
                }
            }
        })
    }

    fun movieResult(movies: Movies) {
        lstMovies.layoutManager = GridLayoutManager(context, 2)
        lstMovies.adapter = MovieAdapter(movies.resultsIntent)
    }

    fun initInjector() {
        DaggerMovieComponent.builder()
            .movieModule(MovieModule())
            .build()
            .inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }

}