package isfaaghyth.app.jetmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import isfaaghyth.app.movies.ui.MovieActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        _testMovieIntent()
    }

    private fun _testMovieIntent() {
        Thread.sleep(1000)
        startActivity(MovieActivity.intent(this))
        finish()
    }

}
