package dev.szymion.domain.interactor

import dev.szymion.domain.models.Joke
import dev.szymion.domain.repositories.JokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetJokesUseCase @Inject constructor(
    private val jokeRepository: JokeRepository
) {
    suspend fun execute(): List<Joke> {
        return withContext(Dispatchers.IO) {
            jokeRepository.getRandomJokes(10)
        }
    }
}
