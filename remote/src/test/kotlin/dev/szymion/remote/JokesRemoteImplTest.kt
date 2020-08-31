package dev.szymion.remote

import com.appmattus.kotlinfixture.kotlinFixture
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import dev.szymion.data.models.JokeEntity
import dev.szymion.remote.model.Joke
import dev.szymion.remote.model.JokesResponseModel
import dev.szymion.remote.services.FilterOptions
import dev.szymion.remote.services.JokesService
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class JokesRemoteImplTest {

    private val fixture = kotlinFixture { }

    private lateinit var jokesRemoteImpl: JokesRemoteImpl
    private val mockedJokesService: JokesService = mock()

    @Before
    fun setUp() {
        jokesRemoteImpl = JokesRemoteImpl(mockedJokesService)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockedJokesService)
    }

    @Test
    fun `getRandomJokes with no filters`(): Unit = runBlocking {
        val mockedJokes: List<Joke> = fixture()
        val mappedJokes = mockedJokes.map { JokeEntity(it.id, it.joke, it.isExplicit()) }
        prepareJokesResponse(mockedJokes)

        val jokes = jokesRemoteImpl.getRandomJokes(42, false)

        assertEquals(mappedJokes, jokes)
        verify(mockedJokesService).getRandomJokes(42, null)
    }

    @Test
    fun `getRandomJokes filtering explicit ones`(): Unit = runBlocking {
        val mockedJokes: List<Joke> = fixture()
        val mappedJokes = mockedJokes.map { JokeEntity(it.id, it.joke, it.isExplicit()) }
        prepareJokesResponse(mockedJokes)

        val jokes = jokesRemoteImpl.getRandomJokes(42, true)

        assertEquals(mappedJokes, jokes)
        verify(mockedJokesService).getRandomJokes(42, FilterOptions.Explicit.apiParameter)
    }

    @Test
    fun `getRandomJokes filtering explicit ones with negative amount`(): Unit = runBlocking {
        val mockedJokes: List<Joke> = fixture()
        val mappedJokes = mockedJokes.map { JokeEntity(it.id, it.joke, it.isExplicit()) }
        prepareJokesResponse(mockedJokes)

        val jokes = jokesRemoteImpl.getRandomJokes(-42, true)

        assertEquals(mappedJokes, jokes)
        verify(mockedJokesService).getRandomJokes(-42, FilterOptions.Explicit.apiParameter)
    }

    private suspend fun prepareJokesResponse(jokes: List<Joke>) {
        whenever(
            mockedJokesService.getRandomJokes(
                anyOrNull(),
                anyOrNull(),
                anyOrNull()
            )
        ).thenReturn(
            JokesResponseModel(
                jokes
            )
        )
    }
}
