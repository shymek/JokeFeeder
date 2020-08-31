package dev.szymion.data

import com.appmattus.kotlinfixture.kotlinFixture
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import dev.szymion.data.models.JokeEntity
import dev.szymion.data.repository.JokeDataStore
import dev.szymion.domain.models.Joke
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class JokeDataRepositoryTest {

    private val fixture = kotlinFixture { }

    private lateinit var jokeDataRepository: JokeDataRepository
    private val mockedJokeDataStore: JokeDataStore = mock()

    @Before
    fun setUp() {
        jokeDataRepository = JokeDataRepository(mockedJokeDataStore)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockedJokeDataStore)
    }

    @Test
    fun `getRandomJokes with positive amount and no filtering`(): Unit = runBlocking {
        val mockedJokes: List<JokeEntity> = fixture()
        val mappedJokes = mockedJokes.map { Joke(it.id, it.joke, it.isExplicit) }
        prepareJokesResponse(mockedJokes)

        val jokes = jokeDataRepository.getRandomJokes(42, false)

        assertEquals(mappedJokes, jokes)
        verify(mockedJokeDataStore).getRandomJokes(42, false)
    }

    @Test
    fun `getRandomJokes with positive amount and filtering`(): Unit = runBlocking {
        val mockedJokes: List<JokeEntity> = fixture()
        val mappedJokes = mockedJokes.map { Joke(it.id, it.joke, it.isExplicit) }
        prepareJokesResponse(mockedJokes)

        val jokes = jokeDataRepository.getRandomJokes(42, true)

        assertEquals(mappedJokes, jokes)
        verify(mockedJokeDataStore).getRandomJokes(42, true)
    }

    @Test
    fun `getRandomJokes with negative amount and filtering`(): Unit = runBlocking {
        val mockedJokes: List<JokeEntity> = fixture()
        val mappedJokes = mockedJokes.map { Joke(it.id, it.joke, it.isExplicit) }
        prepareJokesResponse(mockedJokes)

        val jokes = jokeDataRepository.getRandomJokes(-42, true)

        assertEquals(mappedJokes, jokes)
        verify(mockedJokeDataStore).getRandomJokes(-42, true)
    }

    @Test
    fun `getRandomJokes with negative amount and no filtering`(): Unit = runBlocking {
        val mockedJokes: List<JokeEntity> = fixture()
        val mappedJokes = mockedJokes.map { Joke(it.id, it.joke, it.isExplicit) }
        prepareJokesResponse(mockedJokes)

        val jokes = jokeDataRepository.getRandomJokes(-42, false)

        assertEquals(mappedJokes, jokes)
        verify(mockedJokeDataStore).getRandomJokes(-42, false)
    }

    private suspend fun prepareJokesResponse(jokes: List<JokeEntity>) {
        whenever(mockedJokeDataStore.getRandomJokes(anyOrNull(), anyOrNull())).thenReturn(jokes)
    }
}
