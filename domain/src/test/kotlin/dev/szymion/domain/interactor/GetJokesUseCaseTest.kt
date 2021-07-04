package dev.szymion.domain.interactor

import dev.szymion.domain.models.Joke
import dev.szymion.domain.repositories.JokeRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class GetJokesUseCaseTest {

    lateinit var getJokesUseCase: GetJokesUseCase

    private val mockedJokeRepository: JokeRepository = mock()

    @Before
    fun setUp() {
        getJokesUseCase = GetJokesUseCase(mockedJokeRepository)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(mockedJokeRepository)
    }

    @Test
    fun `execute with positive amount and filtering set to true`(): Unit = runBlocking {
        val mockList: List<Joke> = listOf(mock(), mock(), mock())
        whenever(mockedJokeRepository.getRandomJokes(any(), eq(true))).thenReturn(mockList)

        val result = getJokesUseCase.execute(3, true)

        assertEquals(mockList, result)
        verify(mockedJokeRepository).getRandomJokes(3, true)
    }

    @Test
    fun `execute with positive amount and filtering set to false`(): Unit = runBlocking {
        val mockList: List<Joke> = listOf(mock(), mock(), mock())
        whenever(mockedJokeRepository.getRandomJokes(any(), eq(false))).thenReturn(mockList)

        val result = getJokesUseCase.execute(42, false)

        assertEquals(mockList, result)
        verify(mockedJokeRepository).getRandomJokes(42, false)
    }

    @Test
    fun `execute with negative amount and filtering set to true`(): Unit = runBlocking {
        val mockList: List<Joke> = listOf(mock(), mock(), mock())
        whenever(mockedJokeRepository.getRandomJokes(any(), eq(false))).thenReturn(mockList)

        val result = getJokesUseCase.execute(-42, false)

        assertEquals(mockList, result)
        verify(mockedJokeRepository).getRandomJokes(-42, false)
    }

    @Test
    fun `execute with zero amount and filtering set to true`(): Unit = runBlocking {
        val mockList: List<Joke> = listOf(mock(), mock(), mock())
        whenever(mockedJokeRepository.getRandomJokes(any(), eq(false))).thenReturn(mockList)

        val result = getJokesUseCase.execute(0, false)

        assertEquals(mockList, result)
        verify(mockedJokeRepository).getRandomJokes(0, false)
    }
}
