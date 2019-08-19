package isfaaghyth.app.abstraction.helper

import androidx.test.espresso.idling.CountingIdlingResource

class EspressoIdlingResource {
    companion object {
        private const val RESOURCE = "GLOBAL"
        private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)
        fun increment() = espressoTestIdlingResource.increment()
        fun decrement() {
            if (espressoTestIdlingResource.isIdleNow) {
                espressoTestIdlingResource.decrement()
            }
        }
        fun get() = espressoTestIdlingResource
    }
}