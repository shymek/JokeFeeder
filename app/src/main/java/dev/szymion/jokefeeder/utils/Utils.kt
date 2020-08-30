package dev.szymion.jokefeeder.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import dev.szymion.domain.base.Status
import kotlin.reflect.KSuspendFunction1

fun <T, R> KSuspendFunction1<T, R>.asLiveDataStatus(param: T): LiveData<Status<R>> =
    liveData {
        emit(Status.Loading)
        try {
            emit(Status.Result(this@asLiveDataStatus(param)))
        } catch (e: Throwable) {
            emit(Status.Failure(e))
        }
    }
