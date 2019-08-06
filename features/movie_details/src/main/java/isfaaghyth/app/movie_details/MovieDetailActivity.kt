package isfaaghyth.app.movie_details

import android.content.Context
import android.content.Intent
import isfaaghyth.app.abstraction.base.BaseActivity
import isfaaghyth.app.abstraction.util.toast
import isfaaghyth.app.data.Movie

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
            toast(movie.title)
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

        fun tvShowIntent(context: Context): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_KEY, "")
            return intent
        }
    }

}