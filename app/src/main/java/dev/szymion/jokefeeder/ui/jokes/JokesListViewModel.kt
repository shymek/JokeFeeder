package dev.szymion.jokefeeder.ui.jokes

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.recyclerview.widget.DiffUtil
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
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList

class JokesListViewModel @ViewModelInject constructor(
    private val getJokesUseCase: GetJokesUseCase,
    private val getRandomNumberUseCase: GetRandomNumberUseCase
) : BaseViewModel<JokesListNavigationAction>() {

    val jokes = DiffObservableList(prepareJokesDiffItemCallback())
    val jokesBinding: ItemBinding<Joke> = ItemBinding.of(BR.model, R.layout.row_joke)
    val areJokesLoading = ObservableBoolean()
    var filterExplicit = false

    init {
        loadJokes()
    }

    fun loadJokes() {
        if (areJokesLoading.get()) return

        getJokesUseCase::execute.asLiveDataStatus(
            getRandomNumberUseCase.execute(),
            filterExplicit
        )
            .observeForever {
                when (it) {
                    is Status.Result -> handleNewJokes(it.data)
                    Status.Loading -> areJokesLoading.set(true)
                    is Status.Failure -> handleJokesLoadingError()
                }
            }
    }

    fun setExplicitFilter(shouldFilter: Boolean) {
        filterExplicit = shouldFilter
        if (filterExplicit) {
            jokes.update(jokes.filter { !it.isExplicit })
        }
    }

    private fun handleNewJokes(data: List<Joke>) {
        jokes.update(jokes + data)
        areJokesLoading.set(false)
    }

    private fun handleJokesLoadingError() {
        pushNavigationAction(ShowJokesLoadingError)
        areJokesLoading.set(false)
    }

    private fun prepareJokesDiffItemCallback(): DiffUtil.ItemCallback<Joke> {
        return object : DiffUtil.ItemCallback<Joke>() {
            override fun areItemsTheSame(oldItem: Joke, newItem: Joke) = oldItem === newItem
            override fun areContentsTheSame(oldItem: Joke, newItem: Joke) = oldItem == newItem
        }
    }
}
