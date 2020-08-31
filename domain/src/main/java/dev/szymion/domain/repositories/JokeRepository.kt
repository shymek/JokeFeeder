package dev.szymion.domain.repositories

import dev.szymion.domain.models.Joke

interface JokeRepository {
    suspend fun getRandomJokes(amount: Int, filterExplicit: Boolean): List<Joke>
}
