package dev.szymion.jokefeeder.ui.jokes

import androidx.lifecycle.Observer
import com.appmattus.kotlinfixture.kotlinFixture
import dev.szymion.domain.interactor.GetJokesUseCase
import dev.szymion.domain.interactor.GetRandomNumberUseCase
import dev.szymion.domain.models.Joke
import dev.szymion.jokefeeder.base.BaseTestCase
import java.lang.NullPointerException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class JokesListViewModelTest : BaseTestCase() {

    private val fixture = kotlinFixture { }

    private lateinit var jokesListViewModel: JokesListViewModel
    private val mockedGetJokesUseCase: GetJokesUseCase = mock()
    private val mockedGetRandomNumberUseCase: GetRandomNumberUseCase = mock()
    private val navigationActionObserver = mock<Observer<JokesListNavigationAction>>()

    @Before
    override fun setUp() {
        super.setUp()
        jokesListViewModel = JokesListViewModel(mockedGetJokesUseCase, mockedGetRandomNumberUseCase)
        jokesListViewModel.navigationActions.observeForever(navigationActionObserver)
    }

    @After
    override fun tearDown() {
        verifyNoMoreInteractions(mockedGetJokesUseCase)
        verifyNoMoreInteractions(mockedGetRandomNumberUseCase)
        verifyNoMoreInteractions(navigationActionObserver)
        super.tearDown()
    }

    @Test
    fun `loadJokesIfEmpty with existing jokes`(): Unit = runBlocking {
        val mockedJokes: List<Joke> = fixture()
        jokesListViewModel.jokes.update(mockedJokes)

        jokesListViewModel.loadJokesIfEmpty()

        assertEquals(mockedJokes, jokesListViewModel.jokes)
    }

    @Test
    fun `loadJokesIfEmpty without existing jokes`(): Unit = runBlocking {
        val mockedJokes: List<Joke> = fixture()
        whenever(mockedGetRandomNumberUseCase.execute(anyOrNull(), anyOrNull())).thenReturn(42)
        whenever(mockedGetJokesUseCase.execute(anyOrNull(), anyOrNull())).thenReturn(mockedJokes)

        jokesListViewModel.loadJokesIfEmpty()

        assertEquals(mockedJokes, jokesListViewModel.jokes)
        assert(!jokesListViewModel.areJokesLoading.get())
        verify(mockedGetJokesUseCase).execute(42, false)
        verify(mockedGetRandomNumberUseCase).execute()
    }

    @Test
    fun `setExplicitFilter removes existing explicit jokes`(): Unit = runBlocking {
        val validJoke = Joke(1, "ok joke", false)
        val explicitJoke = Joke(2, "not ok joke", true)
        jokesListViewModel.jokes.update(listOf(validJoke, explicitJoke))

        jokesListViewModel.setExplicitFilter(true)

        assertEquals(listOf(validJoke), jokesListViewModel.jokes)
    }

    @Test
    fun `loadJokes success without filters`(): Unit = runBlocking {
        val mockedJokes: List<Joke> = fixture()
        whenever(mockedGetRandomNumberUseCase.execute(anyOrNull(), anyOrNull())).thenReturn(42)
        whenever(mockedGetJokesUseCase.execute(anyOrNull(), anyOrNull())).thenReturn(mockedJokes)

        jokesListViewModel.loadJokes()
        assertEquals(mockedJokes, jokesListViewModel.jokes)
        assert(!jokesListViewModel.areJokesLoading.get())
        verify(mockedGetJokesUseCase).execute(42, false)
        verify(mockedGetRandomNumberUseCase).execute()
    }

    @Test
    fun `loadJokes success with filters`(): Unit = runBlocking {
        val mockedJokes: List<Joke> = fixture()
        whenever(mockedGetRandomNumberUseCase.execute(anyOrNull(), anyOrNull())).thenReturn(42)
        whenever(mockedGetJokesUseCase.execute(anyOrNull(), anyOrNull())).thenReturn(mockedJokes)

        jokesListViewModel.setExplicitFilter(true)
        jokesListViewModel.loadJokes()
        assertEquals(mockedJokes, jokesListViewModel.jokes)
        assert(!jokesListViewModel.areJokesLoading.get())
        verify(mockedGetJokesUseCase).execute(42, true)
        verify(mockedGetRandomNumberUseCase).execute()
    }

    @Test
    fun `loadJokes with error`(): Unit = runBlocking {
        whenever(mockedGetRandomNumberUseCase.execute(anyOrNull(), anyOrNull())).thenReturn(42)
        whenever(mockedGetJokesUseCase.execute(anyOrNull(), anyOrNull())).thenThrow(
            NullPointerException()
        )

        jokesListViewModel.loadJokes()

        verify(mockedGetRandomNumberUseCase).execute()
        verify(mockedGetJokesUseCase).execute(42, false)
        verify(navigationActionObserver).onChanged(JokesListNavigationAction.ShowJokesLoadingError)
    }
}
