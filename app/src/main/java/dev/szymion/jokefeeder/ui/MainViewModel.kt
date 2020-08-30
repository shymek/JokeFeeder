package dev.szymion.jokefeeder.ui

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import dev.szymion.domain.base.Status
import dev.szymion.domain.interactor.GetJokesUseCase
import dev.szymion.domain.interactor.GetRandomNumberUseCase
import dev.szymion.domain.models.Joke
import dev.szymion.jokefeeder.utils.asLiveDataStatus

class MainViewModel @ViewModelInject constructor(
    private val getJokesUseCase: GetJokesUseCase,
    private val getRandomNumberUseCase: GetRandomNumberUseCase
) : ViewModel() {

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
