package dev.szymion.domain.interactor

import java.security.SecureRandom
import javax.inject.Inject

class GetRandomNumberUseCase @Inject constructor() {
    fun execute(from: Int = DEFAULT_FROM, to: Int = DEFAULT_TO): Int {
        if (from >= to) throw IllegalArgumentException("to must be greater than from")

        val secureRandom = SecureRandom()
        return secureRandom.nextInt(to - from + 1) + from
    }

    companion object {
        private const val DEFAULT_FROM = 8
        private const val DEFAULT_TO = 21
    }
}
