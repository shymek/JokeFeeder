package dev.szymion.remote

import dev.szymion.data.models.JokeEntity
import dev.szymion.data.repository.JokesRemote
import dev.szymion.remote.model.Joke
import dev.szymion.remote.services.JokesService
import javax.inject.Inject

class JokesRemoteImpl @Inject constructor(
    private val jokesService: JokesService
) : JokesRemote {
    override suspend fun getRandomJokes(amount: Int): List<JokeEntity> =
        jokesService.getRandomJokes(amount).jokes.map { it.toEntity() }

    private fun Joke.toEntity(): JokeEntity = JokeEntity(id, joke)
}
