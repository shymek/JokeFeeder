package dev.szymion.domain.interactor

import java.security.SecureRandom
import javax.inject.Inject

class GetRandomNumberUseCase @Inject constructor() {
    fun execute(from: Int = 8, to: Int = 21): Int {
        if (from >= to) throw IllegalArgumentException("to must be greater than from")

        val secureRandom = SecureRandom()
        return secureRandom.nextInt(to - from + 1) + from
    }
}
