package dev.szymion.domain.interactor

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetRandomNumberUseCaseTest {

    lateinit var getRandomNumberUseCase: GetRandomNumberUseCase

    @BeforeEach
    fun setUp() {
        getRandomNumberUseCase = GetRandomNumberUseCase()
    }

    @Test
    fun `execute with invalid arguments from higher than to`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            getRandomNumberUseCase.execute(2, 1)
        }
    }

    @Test
    fun `execute with invalid arguments from equal as to`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            getRandomNumberUseCase.execute(2, 2)
        }
    }

    @Test
    fun `execute with proper arguments returns proper value`() {
        val from = 8
        val to = 21

        for (x in 1..100) {
            val randomNumber = getRandomNumberUseCase.execute(from, to)
            assert(randomNumber >= from)
            assert(randomNumber <= to)
        }
    }
}
