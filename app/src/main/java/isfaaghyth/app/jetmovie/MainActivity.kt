package isfaaghyth.app.jetmovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import isfaaghyth.app.movies.ui.MovieFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.test, MovieFragment())
            .commit()
    }

}
