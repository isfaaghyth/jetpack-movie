package isfaaghyth.app.abstraction.helper

import androidx.annotation.Nullable
import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

class SimpleIdleResource: IdlingResource {

    @Nullable
    @Volatile
    private lateinit var mCallback: IdlingResource.ResourceCallback

    private val mIsIdleNow = AtomicBoolean(true)


    override fun getName(): String {
        return this.javaClass.name
    }

    override fun isIdleNow(): Boolean {
        return mIsIdleNow.get()
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        mCallback = callback
    }

    /**
     * Sets the new idle state, if isIdleNow is true, it pings the [ResourceCallback].
     * @param isIdleNow false if there are pending operations, true if idle.
     */
    fun setIdleState(isIdleNow: Boolean) {
        mIsIdleNow.set(isIdleNow)
        if (isIdleNow) {
            mCallback.onTransitionToIdle()
        }
    }

}