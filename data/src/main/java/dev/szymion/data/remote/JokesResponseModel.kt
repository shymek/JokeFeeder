package dev.szymion.data.remote

import com.google.gson.annotations.SerializedName

data class JokesResponseModel(
    @SerializedName("value")
    val jokes: List<Joke>
)
