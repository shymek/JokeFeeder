package dev.szymion.jokefeeder.ui.jokes

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import dev.szymion.domain.base.Status
import dev.szymion.domain.interactor.GetJokesUseCase
import dev.szymion.domain.interactor.GetRandomNumberUseCase
import dev.szymion.domain.models.Joke
import dev.szymion.jokefeeder.R
import dev.szymion.jokefeeder.ui.base.BaseViewModel
import dev.szymion.jokefeeder.utils.asLiveDataStatus

class JokesListViewModel @ViewModelInject constructor(
    private val getJokesUseCase: GetJokesUseCase,
    private val getRandomNumberUseCase: GetRandomNumberUseCase
) : BaseViewModel<JokesListNavigationAction>() {

    val titleId = ObservableField(R.string.title_jokes)
    val jokes = ObservableArrayList<Joke>()
    val areJokesLoading = ObservableBoolean()

    fun getJokes() {
        getJokesUseCase::execute.asLiveDataStatus(getRandomNumberUseCase.execute())
            .observeForever {
                when (it) {
                    is Status.Result -> handleNewJokes(it.data)
                    Status.Loading -> areJokesLoading.set(true)
                    is Status.Failure -> handleJokesLoadingError()
                }
            }
    }

    private fun handleNewJokes(data: List<Joke>) {
        jokes.addAll(data)
    }

    private fun handleJokesLoadingError() {
        Log.d("MVM", "error when loading jokes")
    }
}
