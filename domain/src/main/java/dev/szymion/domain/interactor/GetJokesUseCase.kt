package dev.szymion.domain.interactor

import dev.szymion.domain.models.Joke
import dev.szymion.domain.repositories.JokeRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetJokesUseCase @Inject constructor(
    private val jokeRepository: JokeRepository
) {
    suspend fun execute(amount: Int, filterExplicit: Boolean = false): List<Joke> {
        return withContext(Dispatchers.IO) {
            jokeRepository.getRandomJokes(amount, filterExplicit)
        }
    }
}
