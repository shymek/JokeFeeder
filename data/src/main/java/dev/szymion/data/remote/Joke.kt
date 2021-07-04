package dev.szymion.data.remote

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("id")
    val id: Long,
    @SerializedName("joke")
    val joke: String,
    @SerializedName("categories")
    val categories: List<JokeCategory>
) {
    fun isExplicit() =
        categories.any { it == JokeCategory.Explicit }
}
