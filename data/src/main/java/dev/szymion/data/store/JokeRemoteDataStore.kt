package dev.szymion.data.store

import dev.szymion.data.models.JokeEntity
import dev.szymion.data.repository.JokeDataStore
import dev.szymion.data.repository.JokesRemote
import javax.inject.Inject

class JokeRemoteDataStore @Inject constructor(
    private val jokesRemote: JokesRemote
) : JokeDataStore {
    override suspend fun getRandomJokes(amount: Int, filterExplicit: Boolean): List<JokeEntity> {
        return jokesRemote.getRandomJokes(amount, filterExplicit)
    }
}
