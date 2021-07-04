package dev.szymion.data.services

import dev.szymion.data.remote.JokesResponseModel
import dev.szymion.data.services.EscapeOptions.JavaScript
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JokesService {
    @GET("/jokes/random/{amount}")
    suspend fun getRandomJokes(
        @Path("amount") amount: Int,
        @Query("exclude") exclude: String? = null,
        @Query("escape") escape: String = JavaScript.apiParameter
    ): JokesResponseModel
}
