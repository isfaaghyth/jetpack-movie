package isfaaghyth.app.movie_details.ui

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import isfaaghyth.app.abstraction.base.BaseActivity
import isfaaghyth.app.abstraction.util.ext.toast
import isfaaghyth.app.movie_details.R
import isfaaghyth.app.movie_details.di.DaggerMovieDetailComponent
import java.lang.Exception
import javax.inject.Inject

class MovieDetailActivity: BaseActivity() {

    override fun contentView(): Int = R.layout.activity_movie_detail

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MovieDetailViewModel

    override fun initView() {
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(MovieDetailViewModel::class.java)

        initObserver()
    }

    private fun initObserver() {
        viewModel.movieDetail.observe(this, Observer {
            Log.d("TAG", it.title)
        })
        viewModel.tvDetail.observe(this, Observer {
            Log.d("TAG", it.title)
        })
        viewModel.error.observe(this, Observer {
            toast(it)
        })
    }

    override fun initInjector() {
        DaggerMovieDetailComponent.builder()
            .movieDetailModule(MovieDetailModule())
            .build()
            .inject(this)
    }
}