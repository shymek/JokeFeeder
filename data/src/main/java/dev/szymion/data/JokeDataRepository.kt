package dev.szymion.data

import dev.szymion.data.models.JokeEntity
import dev.szymion.data.repository.JokesRemote
import dev.szymion.domain.models.Joke
import dev.szymion.domain.repositories.JokeRepository
import javax.inject.Inject

class JokeDataRepository @Inject constructor(
    private val remoteStore: JokesRemote
) : JokeRepository {
    override suspend fun getRandomJokes(amount: Int, filterExplicit: Boolean): List<Joke> {
        return remoteStore.getRandomJokes(amount, filterExplicit).map { it.toDomain() }
    }

    private fun JokeEntity.toDomain(): Joke = Joke(id, joke, isExplicit)
}
