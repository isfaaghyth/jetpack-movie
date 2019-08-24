package isfaaghyth.app.abstraction.helper

import androidx.test.espresso.IdlingResource

//fetcher contract
interface FetcherListener {
    fun begin()
    fun complete()
}

//singleton idling resources
object FetchingIdlingResource {
    private var idlingResource = EspressoIdlingResource()
    fun begin() = idlingResource.begin()
    fun complete() = idlingResource.complete()
    fun get(): EspressoIdlingResource = idlingResource
}

class EspressoIdlingResource: IdlingResource, FetcherListener {
    private var idle = true
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return EspressoIdlingResource::class.java.simpleName
    }

    override fun isIdleNow() = idle

    override fun registerIdleTransitionCallback(
        callback: IdlingResource.ResourceCallback?) {
        resourceCallback = callback
    }

    override fun complete() {
        idle = true
        resourceCallback?.onTransitionToIdle()
    }

    override fun begin() {
        idle = false
    }
}