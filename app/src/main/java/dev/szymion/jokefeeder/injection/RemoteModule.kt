package dev.szymion.jokefeeder.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dev.szymion.data.repository.JokesRemote
import dev.szymion.jokefeeder.BuildConfig
import dev.szymion.data.JokesRemoteImpl
import dev.szymion.data.services.JokesService
import dev.szymion.data.services.JokesServiceFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RemoteModule {

    @Provides
    @Singleton
    fun provideJokesRemote(jokesRemoteImp: JokesRemoteImpl): JokesRemote {
        return jokesRemoteImp
    }

    @Provides
    @Singleton
    fun provideJokesService(): JokesService {
        return JokesServiceFactory.create(
            BuildConfig.DEBUG,
            BuildConfig.BASE_URL
        )
    }
}
