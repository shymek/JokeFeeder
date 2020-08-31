package dev.szymion.jokefeeder.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dev.szymion.data.JokeDataRepository
import dev.szymion.data.repository.JokeDataStore
import dev.szymion.data.store.JokeRemoteDataStore
import dev.szymion.domain.repositories.JokeRepository
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideJokeRemoteDataStore(jokeDataStore: JokeRemoteDataStore): JokeDataStore {
        return jokeDataStore
    }

    @Provides
    @Singleton
    fun provideJokeRepository(jokeDataRepository: JokeDataRepository): JokeRepository {
        return jokeDataRepository
    }
}
