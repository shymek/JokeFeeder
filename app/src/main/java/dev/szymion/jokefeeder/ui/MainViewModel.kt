package dev.szymion.jokefeeder.ui

import androidx.databinding.ObservableArrayList
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.szymion.domain.interactor.GetJokesUseCase
import dev.szymion.domain.interactor.GetRandomNumberUseCase
import dev.szymion.domain.models.Joke
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val getJokesUseCase: GetJokesUseCase,
    private val getRandomNumberUseCase: GetRandomNumberUseCase
) : ViewModel() {

    val jokes = ObservableArrayList<Joke>()

    fun getJokes() {
        viewModelScope.launch {
            val newJokes = getJokesUseCase.execute(getRandomNumberUseCase.execute())
            jokes.clear()
            jokes.addAll(newJokes)
        }
    }
}
