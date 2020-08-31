package dev.szymion.jokefeeder.ui.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.szymion.jokefeeder.R
import dev.szymion.jokefeeder.databinding.FragmentJokesListBinding
import dev.szymion.jokefeeder.utils.addOnLoadMoreListener

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_jokes_list, menu)
        menu.findItem(R.id.menuFilterExplicit).isChecked = viewModel.filterExplicit
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuFilterExplicit -> handleFilterToggle(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadJokesIfEmpty()
        setHasOptionsMenu(true)
        observeNavigationActions()
        initializeScrollListener()
    }

    private fun observeNavigationActions() {
        viewModel.navigationActions.observe(this, { handleNavigationAction(it) })
    }

    private fun handleFilterToggle(item: MenuItem) {
        item.isChecked = !item.isChecked
        viewModel.setExplicitFilter(item.isChecked)
    }

    private fun handleNavigationAction(action: JokesListNavigationAction) {
        when (action) {
            JokesListNavigationAction.ShowJokesLoadingError -> showJokesLoadingError()
        }
    }

    private fun showJokesLoadingError() {
        Snackbar.make(binding.root, R.string.error_loading_jokes, Snackbar.LENGTH_SHORT)
    }

    private fun initializeScrollListener() {
        binding.rvJokes.addOnLoadMoreListener(viewModel::loadJokes, LOAD_MORE_THRESHOLD)
    }

    companion object {
        private const val LOAD_MORE_THRESHOLD = 1
    }
}
