package isfaaghyth.app.movie_details

import android.content.Context
import android.content.Intent
import isfaaghyth.app.abstraction.base.BaseActivity
import isfaaghyth.app.abstraction.util.load
import isfaaghyth.app.abstraction.util.toast
import isfaaghyth.app.data.Movie
import isfaaghyth.app.data.TVShow
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity: BaseActivity() {

    override fun contentView(): Int = R.layout.activity_movie_detail

    override fun initView() {
        try {
            getMovieData()
            getTvShowData()
        } catch (e: Exception) {
            toast("something wrong!")
            finish()
        }
    }

    private fun getMovieData() {
        intent?.let {
            val movie = it.getParcelableExtra<Movie>(MOVIE_KEY)
            imgBanner.load(movie.bannerUrl())
            imgPoster.load(movie.posterUrl())
            txtMovieName.text = movie.title
            txtContent.text = movie.overview
            txtYear.text = movie.releaseDate
            txtRating.text = "${movie.voteAverage}"
            txtVote.text = "${movie.voteCount}"
        }
    }

    private fun getTvShowData() {

    }

    override fun initInjector() = Unit //Nothing to inject

    companion object {
        private const val MOVIE_KEY = "movie"
        private const val TV_SHOW_KEY = "tvshow"

        fun movieIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_KEY, movie)
            return intent
        }

        fun tvShowIntent(context: Context, tvShow: TVShow): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(TV_SHOW_KEY, tvShow)
            return intent
        }
    }

}