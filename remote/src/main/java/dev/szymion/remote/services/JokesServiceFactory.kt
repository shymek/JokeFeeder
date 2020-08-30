package dev.szymion.remote.services

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JokesServiceFactory {

    fun create(isDebugMode: Boolean, baseUrl: String): JokesService {
        val client = createOkHttp(isDebugMode)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        return retrofit.create(JokesService::class.java)
    }

    private fun createOkHttp(isDebugMode: Boolean): OkHttpClient {
        val logging = HttpLoggingInterceptor()

        if (isDebugMode) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else logging.level = HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
}
