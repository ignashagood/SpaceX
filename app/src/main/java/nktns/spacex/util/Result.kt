package nktns.spacex.util

sealed class Result<out T : Any> {
    class Success<out T : Any>(val data: T) : Result<T>()
    class Error(val throwable: Throwable?) : Result<Nothing>()
}
