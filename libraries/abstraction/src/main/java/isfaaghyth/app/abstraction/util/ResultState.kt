package isfaaghyth.app.abstraction.util

import java.lang.Exception

sealed class ResultState<out T: Any> {
    data class Success<out T: Any>(val data: T): ResultState<T>()
    data class Error(val error: Exception): ResultState<Nothing>()
}