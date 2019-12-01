package isfaaghyth.app.movie_details.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.airbnb.deeplinkdispatch.DeepLink
import isfaaghyth.app.abstraction.base.BaseActivity
import isfaaghyth.app.abstraction.util.AppLink.MovieDetail.MOVIE_DETAIL
import isfaaghyth.app.abstraction.util.AppLink.MovieDetail.PARAM_MOVIE_ID
import isfaaghyth.app.abstraction.util.AppLink.MovieDetail.PARAM_TYPE
import isfaaghyth.app.abstraction.util.ext.hide
import isfaaghyth.app.abstraction.util.ext.load
import isfaaghyth.app.abstraction.util.ext.show
import isfaaghyth.app.abstraction.util.ext.toast
import isfaaghyth.app.abstraction.util.state.LoaderState
import isfaaghyth.app.data.entity.MovieDetail
import isfaaghyth.app.data.mapper.MovieDetailMapper
import isfaaghyth.app.movie_details.R
import isfaaghyth.app.movie_details.di.DaggerMovieDetailComponent
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject

@DeepLink(MOVIE_DETAIL)
class MovieDetailActivity: BaseActivity() {

    override fun contentView(): Int = R.layout.activity_movie_detail

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MovieDetailViewModel

    override fun initView() {
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(MovieDetailViewModel::class.java)

        initParam()
        initObservable()
    }

    private fun initParam() {
        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            val parameters = intent.extras?: Bundle()
            val movieId = parameters.getString(PARAM_MOVIE_ID)?: ""

            when(parameters.getString(PARAM_TYPE)) {
                TYPE_MOVIE -> viewModel.getMovieDetail(movieId)
                TYPE_TV -> viewModel.getTVShowDetail(movieId)
                else -> finish()
            }
        }
    }

    private fun initObservable() {
        viewModel.state.observe(this, Observer {
            when (it) {
                is LoaderState.ShowLoading -> {
                    rootView.hide()
                    progressBar.show()
                }
                is LoaderState.HideLoading -> {
                    rootView.show()
                    progressBar.hide()
                }
            }
        })

        viewModel.movieDetail.observe(this, Observer { movie ->
            showDetail(MovieDetailMapper.mapFromMovie(movie))
        })

        viewModel.tvDetail.observe(this, Observer { tv ->
            showDetail(MovieDetailMapper.mapFromTVShow(tv))
        })

        viewModel.error.observe(this, Observer { toast(it) })
    }

    private fun showDetail(detail: MovieDetail) {
        imgBanner.load(banner)
        imgPoster.load(detail.posterPath)
        txtMovieName.text = title
        txtContent.text = detail.overview
        txtRating.text = detail.voteCount.toString()
        txtVote.text = detail.voteAverage.toString()
    }

    override fun initInjector() {
        DaggerMovieDetailComponent.builder()
            .movieDetailModule(MovieDetailModule())
            .build()
            .inject(this)
    }

    companion object {
        const val TYPE_MOVIE = "movie"
        const val TYPE_TV = "tv"
    }

}