package isfaaghyth.app.abstraction.util

sealed class ResultState<out T: Any> {
    data class Success<out T: Any>(val data: T): ResultState<T>()
    data class Error(val error: String?): ResultState<Nothing>()
}