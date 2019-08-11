package isfaaghyth.app.jetmovie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    private var mIdlingResource: IdlingResource? = null

    @Before fun registerIdleResource() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            mIdlingResource = activity.getIdlingResource()
            IdlingRegistry.getInstance().register(mIdlingResource)
        }
    }

    @Test fun testMovieList() {
        val itemPosition = 5
        Thread.sleep(1500)

        //showing movies
        onView(withId(R.id.btnMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.btnMovie)).perform(click())

        //check visibility of listMovies
        onView(withId(R.id.lstMovies)).check(matches(isDisplayed()))

        Thread.sleep(1500)

        //scroll movie list into 5
        onView(withId(R.id.lstMovies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemPosition))

        //goto movieDetailActivity
        onView(withId(R.id.lstMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(itemPosition, click()))

        //check visibility of image of banner
        onView(withId(R.id.imgBanner)).check(matches(isDisplayed()))

        //back to mainActivity
        pressBack()
    }

    @Test fun testTvShowList() {
        val itemPosition = 5
        Thread.sleep(1500)

        //showing movies
        onView(withId(R.id.btnTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.btnTvShow)).perform(click())

        //check visibility of listMovies
        onView(withId(R.id.lstTvShows)).check(matches(isDisplayed()))

        Thread.sleep(1500)

        //scroll movie list into 5
        onView(withId(R.id.lstTvShows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemPosition))

        //goto movieDetailActivity
        onView(withId(R.id.lstTvShows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(itemPosition, click()))

        //check visibility of image of banner
        onView(withId(R.id.imgBanner)).check(matches(isDisplayed()))

        //back to mainActivity
        pressBack()
    }

    @After fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource)
        }
    }

}