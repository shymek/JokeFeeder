package dev.szymion.data.repository

import dev.szymion.data.models.JokeEntity

interface JokeDataStore {
    suspend fun getRandomJokes(amount: Int): List<JokeEntity>
}
