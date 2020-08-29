package dev.szymion.remote.services

import dev.szymion.remote.model.JokesResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface JokesService {
    @GET("/jokes/random/{amount}")
    suspend fun getRandomJokes(@Path("amount") amount: Int): JokesResponseModel
}
