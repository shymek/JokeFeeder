package dev.szymion.jokefeeder.ui.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.szymion.jokefeeder.R
import dev.szymion.jokefeeder.databinding.FragmentJokesListBinding

@AndroidEntryPoint
class JokesListFragment : Fragment() {

    private lateinit var binding: FragmentJokesListBinding
    private val viewModel: JokesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_jokes_list, container, false)
        binding.model = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeNavigationActions()
    }

    private fun observeNavigationActions() {
        viewModel.navigationActions.observe(this, Observer { handleNavigationAction(it) })
    }

    private fun handleNavigationAction(action: JokesListNavigationAction) {
        when (action) {
            JokesListNavigationAction.ShowJokesLoadingError -> showJokesLoadingError()
        }
    }

    private fun showJokesLoadingError() {
        showError(R.string.error_loading_jokes)
    }

    private fun showError(@StringRes errorText: Int) {
        Snackbar.make(binding.root, errorText, Snackbar.LENGTH_SHORT).show()
    }
}
