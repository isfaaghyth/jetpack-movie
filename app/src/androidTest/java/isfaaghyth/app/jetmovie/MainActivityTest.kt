package isfaaghyth.app.jetmovie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import isfaaghyth.app.abstraction.helper.FetchingIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(
        MainActivity::class.java,
        false,
        false)

    private val itemPosition = 5

    @Before fun setUp() {
        activityRule.launchActivity(null)
        IdlingRegistry.getInstance().register(FetchingIdlingResource.get())
    }

    @Test fun testMovieList() {
        //check visibility of listMovies
        onView(withId(R.id.lstMovies)).check(matches(isDisplayed()))

        //scroll movie list into 5
        onView(withId(R.id.lstMovies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemPosition))

        //goto movieDetailActivity
        onView(withId(R.id.lstMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(itemPosition, click()))

        //check visibility of image of banner
        onView(withId(R.id.imgBanner)).check(matches(isDisplayed()))
    }

    @Test fun testTvShowList() {
        onView(withId(R.id.viewpagerMain)).perform(swipeLeft())

        //check visibility of listMovies
        onView(withId(R.id.lstTvShows)).check(matches(isDisplayed()))

        //scroll movie list into 5
        onView(withId(R.id.lstTvShows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemPosition))

        //goto movieDetailActivity
        onView(withId(R.id.lstTvShows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(itemPosition, click()))

        //check visibility of image of banner
        onView(withId(R.id.imgBanner)).check(matches(isDisplayed()))
    }

    @After fun tearDown() {
        IdlingRegistry.getInstance().unregister(FetchingIdlingResource.get())
    }

}