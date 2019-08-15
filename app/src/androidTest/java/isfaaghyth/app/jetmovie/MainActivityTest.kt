package isfaaghyth.app.jetmovie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import isfaaghyth.app.jetmovie.helper.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Rule @JvmField val rules = ActivityTestRule(MainActivity::class.java)

    @Before fun registerIdleResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.get())
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

        Thread.sleep(2000)

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

        Thread.sleep(2000)

        //check visibility of image of banner
        onView(withId(R.id.imgBanner)).check(matches(isDisplayed()))

        //back to mainActivity
        pressBack()
    }

    @After fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.get())
    }

}