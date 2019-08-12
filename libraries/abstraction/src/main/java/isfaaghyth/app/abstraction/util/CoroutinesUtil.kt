package isfaaghyth.app.abstraction.util

import java.io.IOException

suspend fun <T: Any> fetchState(call: suspend () -> ResultState<T>): ResultState<T> {
    return try {
        call.invoke()
    } catch (e: Exception) {
        ResultState.Error(IOException(e.message))
    } catch (e: Throwable) {
        ResultState.Error(IOException(e.message))
    }
}