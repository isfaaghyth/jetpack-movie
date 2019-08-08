package isfaaghyth.app.abstraction.util.thread

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestSchedulerProvider: SchedulerProvider {
    override fun ui(): CoroutineDispatcher = Dispatchers.Unconfined
}