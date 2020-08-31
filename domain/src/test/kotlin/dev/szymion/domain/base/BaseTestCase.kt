package dev.szymion.domain.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@ExperimentalCoroutinesApi
abstract class BaseTestCase {

    @Before
    open fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    open fun tearDown() {
        Dispatchers.resetMain()
    }
}
