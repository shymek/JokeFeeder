package dev.szymion.jokefeeder.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dev.szymion.data.JokeDataRepository
import dev.szymion.domain.repositories.JokeRepository
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideJokeRepository(jokeDataRepository: JokeDataRepository): JokeRepository {
        return jokeDataRepository
    }
}
