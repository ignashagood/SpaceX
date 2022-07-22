package nktns.spacex.util

import retrofit2.Response

suspend fun <T : Any> withExceptionHandling(block: suspend () -> Response<T>): Result<T> {

    return try {
        val response = block()

        if (response.isSuccessful) {
            Result.Success(response.body() as T)
        } else {
            Result.Error(Throwable(response.message() ?: response.code().toString()))
        }
    } catch (e: Exception) {
        Result.Error(Throwable(e.toString()))
    }
}
