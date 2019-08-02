package isfaaghyth.app.jetmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import isfaaghyth.app.movies.ui.MovieActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        startActivity(MovieActivity.intent(this))
    }

}
