package dev.szymion.data

import dev.szymion.data.models.JokeEntity
import dev.szymion.data.remote.Joke
import dev.szymion.data.repository.JokesRemote
import dev.szymion.data.services.FilterOptions
import dev.szymion.data.services.JokesService
import javax.inject.Inject

class JokesRemoteImpl @Inject constructor(
    private val jokesService: JokesService
) : JokesRemote {
    override suspend fun getRandomJokes(amount: Int, filterExplicit: Boolean): List<JokeEntity> =
        jokesService.getRandomJokes(amount, getFilter(filterExplicit)).jokes.map { it.toEntity() }

    private fun Joke.toEntity(): JokeEntity = JokeEntity(id, joke, isExplicit())

    private fun getFilter(shouldFilterExplicit: Boolean) =
        if (shouldFilterExplicit) {
            FilterOptions.Explicit.apiParameter
        } else {
            null
        }
}
