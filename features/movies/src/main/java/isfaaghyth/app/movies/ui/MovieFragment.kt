package isfaaghyth.app.movies.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import isfaaghyth.app.abstraction.util.ext.toast
import isfaaghyth.app.abstraction.util.state.LoaderState
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.movies.R
import isfaaghyth.app.movies.di.DaggerMovieComponent
import kotlinx.android.synthetic.main.fragment_movie.*
import me.ibrahimyilmaz.kiel.adapterOf
import javax.inject.Inject

class MovieFragment : Fragment() {

    private fun contentView(): Int = R.layout.fragment_movie

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MovieViewModel

    private val adapter = adapterOf<Movie> {
        register(
            layoutResource = R.layout.item_movie,
            viewHolder = ::MovieViewHolder,
            onBindViewHolder = { movieViewHolder, _, movie ->
                movieViewHolder.cardItem.setOnClickListener {
                    requireContext().startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("jetmovie://detail/movie/${movie.id}")
                        )
                    )
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(contentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInjector()
        initView()
    }

    private fun initView() {
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(MovieViewModel::class.java)

        lstMovies.adapter = adapter

        initObserver()
    }

    private fun initObserver() {
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is LoaderState.ShowLoading -> toast("loading")
                is LoaderState.HideLoading -> toast("complete")
            }
        })

        viewModel.result.observe(this, Observer(adapter::submitList))

        viewModel.error.observe(this, Observer { error ->
            toast(error)
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