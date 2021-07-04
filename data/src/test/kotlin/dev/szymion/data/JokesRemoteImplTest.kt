package dev.szymion.data

import com.appmattus.kotlinfixture.kotlinFixture
import dev.szymion.data.models.JokeEntity
import dev.szymion.data.remote.Joke
import dev.szymion.data.remote.JokesResponseModel
import dev.szymion.data.repository.JokesRemoteImpl
import dev.szymion.data.services.FilterOptions
import dev.szymion.data.services.JokesService
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

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

        Assert.assertEquals(mappedJokes, jokes)
        verify(mockedJokesService).getRandomJokes(42, null)
    }

    @Test
    fun `getRandomJokes filtering explicit ones`(): Unit = runBlocking {
        val mockedJokes: List<Joke> = fixture()
        val mappedJokes = mockedJokes.map { JokeEntity(it.id, it.joke, it.isExplicit()) }
        prepareJokesResponse(mockedJokes)

        val jokes = jokesRemoteImpl.getRandomJokes(42, true)

        Assert.assertEquals(mappedJokes, jokes)
        verify(mockedJokesService).getRandomJokes(42, FilterOptions.Explicit.apiParameter)
    }

    @Test
    fun `getRandomJokes filtering explicit ones with negative amount`(): Unit = runBlocking {
        val mockedJokes: List<Joke> = fixture()
        val mappedJokes = mockedJokes.map { JokeEntity(it.id, it.joke, it.isExplicit()) }
        prepareJokesResponse(mockedJokes)

        val jokes = jokesRemoteImpl.getRandomJokes(-42, true)

        Assert.assertEquals(mappedJokes, jokes)
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
