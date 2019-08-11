package isfaaghyth.app.jetmovie

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import isfaaghyth.app.movies.ui.MovieFragment
import isfaaghyth.app.tvshows.ui.TVShowFragment
import kotlinx.android.synthetic.main.activity_main.*
import androidx.test.espresso.IdlingResource

class MainActivity : AppCompatActivity() {

    private var mIdlingResource = SimpleIdleResource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //default
        fragmentReplace(MovieFragment())

        btnMovie.setOnClickListener {
            fragmentReplace(MovieFragment())
        }

        btnTvShow.setOnClickListener {
            fragmentReplace(TVShowFragment())
        }
    }

    private fun fragmentReplace(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.test, fragment)
            .commit()
    }

    @VisibleForTesting @NonNull
    fun getIdlingResource(): IdlingResource {
        return mIdlingResource
    }

}
