package isfaaghyth.app.abstraction.util.thread

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ApplicationSchedulerProvider: SchedulerProvider {
    override fun ui(): CoroutineDispatcher = Dispatchers.Main
}