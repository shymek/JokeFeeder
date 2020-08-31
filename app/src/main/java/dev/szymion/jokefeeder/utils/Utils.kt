package dev.szymion.jokefeeder.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import dev.szymion.domain.base.Status
import kotlin.reflect.KSuspendFunction2

fun <P1, P2, R> KSuspendFunction2<P1, P2, R>.asLiveDataStatus(
    firstParam: P1,
    secondParam: P2
): LiveData<Status<R>> =
    liveData {
        emit(Status.Loading)
        try {
            emit(Status.Result(this@asLiveDataStatus(firstParam, secondParam)))
        } catch (e: Throwable) {
            emit(Status.Failure(e))
        }
    }
