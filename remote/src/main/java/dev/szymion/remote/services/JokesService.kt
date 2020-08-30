package dev.szymion.remote.services

import dev.szymion.remote.model.JokesResponseModel
import dev.szymion.remote.services.EscapeOptions.JavaScript
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JokesService {
    @GET("/jokes/random/{amount}")
    suspend fun getRandomJokes(
        @Path("amount") amount: Int,
        @Query("escape") escape: String = JavaScript.apiParameter
    ): JokesResponseModel
}
