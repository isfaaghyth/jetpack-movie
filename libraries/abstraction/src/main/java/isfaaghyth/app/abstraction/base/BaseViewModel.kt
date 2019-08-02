package isfaaghyth.app.abstraction.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.isActive
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(private val baseDispatcher: CoroutineDispatcher): ViewModel(), CoroutineScope {

    protected val supervisorJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = baseDispatcher + supervisorJob

    open fun clear() {
        if (isActive && !supervisorJob.isCancelled) {
            supervisorJob.children.map {
                it.cancel()
            }
        }
    }

}