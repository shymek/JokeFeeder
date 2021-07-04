package dev.szymion.data.store

import com.appmattus.kotlinfixture.kotlinFixture
import dev.szymion.data.models.JokeEntity
import dev.szymion.data.repository.JokesRemote
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class JokeRemoteDataStoreTest {

    private val fixture = kotlinFixture { }

    private lateinit var jokeRemoteDataStore: JokeRemoteDataStore
    private val mockedJokesRemote: JokesRemote = mock()

    @Before
    fun setUp() {
        jokeRemoteDataStore = JokeRemoteDataStore(mockedJokesRemote)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockedJokesRemote)
    }

    @Test
    fun `getRandomJokes with positive amount and no filtering`(): Unit = runBlocking {
        val mockedJokes: List<JokeEntity> = fixture()
        prepareJokesResponse(mockedJokes)

        val jokes = jokeRemoteDataStore.getRandomJokes(42, false)

        assertEquals(mockedJokes, jokes)
        verify(mockedJokesRemote).getRandomJokes(42, false)
    }

    @Test
    fun `getRandomJokes with positive amount and filtering`(): Unit = runBlocking {
        val mockedJokes: List<JokeEntity> = fixture()
        prepareJokesResponse(mockedJokes)

        val jokes = jokeRemoteDataStore.getRandomJokes(42, true)

        assertEquals(mockedJokes, jokes)
        verify(mockedJokesRemote).getRandomJokes(42, true)
    }

    @Test
    fun `getRandomJokes with negative amount and filtering`(): Unit = runBlocking {
        val mockedJokes: List<JokeEntity> = fixture()
        prepareJokesResponse(mockedJokes)

        val jokes = jokeRemoteDataStore.getRandomJokes(-42, true)

        assertEquals(mockedJokes, jokes)
        verify(mockedJokesRemote).getRandomJokes(-42, true)
    }

    @Test
    fun `getRandomJokes with negative amount and no filtering`(): Unit = runBlocking {
        val mockedJokes: List<JokeEntity> = fixture()
        prepareJokesResponse(mockedJokes)

        val jokes = jokeRemoteDataStore.getRandomJokes(-42, false)

        assertEquals(mockedJokes, jokes)
        verify(mockedJokesRemote).getRandomJokes(-42, false)
    }

    private suspend fun prepareJokesResponse(jokes: List<JokeEntity>) {
        whenever(mockedJokesRemote.getRandomJokes(anyOrNull(), anyOrNull())).thenReturn(jokes)
    }
}
