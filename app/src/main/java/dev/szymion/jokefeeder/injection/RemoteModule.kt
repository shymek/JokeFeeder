package dev.szymion.jokefeeder.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dev.szymion.data.repository.JokesRemote
import dev.szymion.jokefeeder.BuildConfig
import dev.szymion.remote.JokesRemoteImpl
import dev.szymion.remote.services.JokesService
import dev.szymion.remote.services.JokesServiceFactory
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
