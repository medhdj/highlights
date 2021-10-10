package com.medhdj.core.functionnal

sealed class Response<out ERROR, out DATA>() {
    data class Failure<ERROR>(val error: ERROR) : Response<ERROR, Nothing>()
    data class Success<DATA>(val data: DATA) : Response<Nothing, DATA>()

    class Loading : Response<Nothing, Nothing>()

    val isLoading get() = this is Loading
    val isSuccess get() = this is Success<DATA>
    val isFailure get() = this is Failure<ERROR>

    fun either(fnError: (ERROR) -> Unit, fnSuccess: (DATA) -> Unit) {
        when (this) {
            is Failure -> fnError(error)
            is Success -> fnSuccess(data)
            is Loading -> {
                // nothing
            }
        }
    }
}
