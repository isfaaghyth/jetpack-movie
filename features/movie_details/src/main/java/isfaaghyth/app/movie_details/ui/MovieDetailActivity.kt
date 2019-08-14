package isfaaghyth.app.movie_details.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.airbnb.deeplinkdispatch.DeepLink
import isfaaghyth.app.abstraction.base.BaseActivity
import isfaaghyth.app.abstraction.util.AppLink
import isfaaghyth.app.abstraction.util.ext.hide
import isfaaghyth.app.abstraction.util.ext.load
import isfaaghyth.app.abstraction.util.ext.show
import isfaaghyth.app.abstraction.util.ext.toast
import isfaaghyth.app.abstraction.util.state.LoaderState
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.movie_details.R
import isfaaghyth.app.movie_details.di.DaggerMovieDetailComponent
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.util.*
import javax.inject.Inject

@DeepLink(AppLink.MOVIE_DETAIL)
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
        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            val parameters = intent.extras!!
            val movieId = parameters.getString(PARAM_MOVIE_ID)?: ""

            when(parameters.getString(PARAM_TYPE)) {
                TYPE_MOVIE -> viewModel.getMovieDetail(movieId)
                TYPE_TV -> viewModel.getTVShowDetail(movieId)
                else -> finish()
            }

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

            viewModel.movieDetail.observe(this, Observer {
                imgBanner.load(it.bannerUrl())
                imgPoster.load(it.posterUrl())
                txtMovieName.text = it.title
                txtContent.text = it.overview
                txtRating.text = "${it.voteCount}"
                txtVote.text = "${it.voteAverage}"
            })

            viewModel.tvDetail.observe(this, Observer {
                imgBanner.load(it.bannerUrl())
                imgPoster.load(it.posterUrl())
                txtMovieName.text = it.title
                txtContent.text = it.overview
                txtRating.text = "${it.voteCount}"
                txtVote.text = "${it.voteAverage}"
            })

            viewModel.error.observe(this, Observer {
                toast(it)
            })
        }
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

        const val PARAM_MOVIE_ID = "movie_id"
        const val PARAM_TYPE = "type"
    }

}