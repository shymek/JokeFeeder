package dev.szymion.data.repository

import dev.szymion.data.models.JokeEntity

interface JokesRemote {
    suspend fun getRandomJokes(amount: Int, filterExplicit: Boolean = false): List<JokeEntity>
}
