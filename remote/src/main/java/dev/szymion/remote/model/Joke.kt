package dev.szymion.remote.model

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("id")
    val id: Long,
    @SerializedName("joke")
    val joke: String
)
