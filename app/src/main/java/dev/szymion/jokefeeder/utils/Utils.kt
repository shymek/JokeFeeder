package dev.szymion.jokefeeder.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

fun RecyclerView.addOnLoadMoreListener(callback: () -> Unit, threshold: Int) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val manager: LinearLayoutManager = layoutManager as LinearLayoutManager
            if (manager.findLastCompletelyVisibleItemPosition() == manager.itemCount - threshold) {
                callback()
            }
        }
    })
}
