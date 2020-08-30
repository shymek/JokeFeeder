package dev.szymion.jokefeeder.ui.jokes

sealed class JokesListNavigationAction {
    object ShowJokesLoadingError : JokesListNavigationAction()
}
