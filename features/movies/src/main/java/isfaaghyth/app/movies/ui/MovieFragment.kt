package isfaaghyth.app.movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import isfaaghyth.app.abstraction.util.MarginItemDecoration
import isfaaghyth.app.abstraction.util.toast
import isfaaghyth.app.movies.R
import isfaaghyth.app.data.Movie
import isfaaghyth.app.movies.di.DaggerMovieComponent
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieFragment: Fragment() {

    private fun contentView(): Int = R.layout.fragment_movie

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MovieViewModel

    private var movieData = arrayListOf<Movie>()
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(movieData)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(contentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInjector()
        initView()
    }

    private fun initView() {
        //view model provider
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(MovieViewModel::class.java)

        lstMovies.adapter = adapter

        //get movies
        initObserver()
    }

    private fun initObserver() {
        //observe it!
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is MovieState.ShowLoading -> toast("loading")
                is MovieState.HideLoading -> toast("complete")
                is MovieState.LoadSuccess -> {
                    movieData.addAll(state.data.resultsIntent)
                    adapter.notifyDataSetChanged()
                }
                is MovieState.MovieError -> {
                    toast("there's problem, please try again")
                }
            }
        })
    }

    private fun initInjector() {
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