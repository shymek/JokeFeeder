package dev.szymion.jokefeeder.ui.jokes

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import dev.szymion.domain.base.Status
import dev.szymion.domain.interactor.GetJokesUseCase
import dev.szymion.domain.interactor.GetRandomNumberUseCase
import dev.szymion.domain.models.Joke
import dev.szymion.jokefeeder.BR
import dev.szymion.jokefeeder.R
import dev.szymion.jokefeeder.ui.base.BaseViewModel
import dev.szymion.jokefeeder.ui.jokes.JokesListNavigationAction.ShowJokesLoadingError
import dev.szymion.jokefeeder.utils.asLiveDataStatus
import me.tatarka.bindingcollectionadapter2.ItemBinding

class JokesListViewModel @ViewModelInject constructor(
    private val getJokesUseCase: GetJokesUseCase,
    private val getRandomNumberUseCase: GetRandomNumberUseCase
) : BaseViewModel<JokesListNavigationAction>() {

    val titleId = ObservableField(R.string.title_jokes)
    val jokes = ObservableArrayList<Joke>()
    val jokesBinding: ItemBinding<Joke> = ItemBinding.of(BR.model, R.layout.row_joke)
    val areJokesLoading = ObservableBoolean()

    init {
        loadJokes()
    }

    fun loadJokes() {
        if (areJokesLoading.get()) return

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
        areJokesLoading.set(false)
    }

    private fun handleJokesLoadingError() {
        pushNavigationAction(ShowJokesLoadingError)
        areJokesLoading.set(false)
    }
}
