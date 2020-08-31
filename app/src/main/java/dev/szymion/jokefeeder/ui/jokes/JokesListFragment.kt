package dev.szymion.jokefeeder.ui.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
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

        setHasOptionsMenu(true)
        observeNavigationActions()
        initializeScrollListener()
    }

    private fun observeNavigationActions() {
        viewModel.navigationActions.observe(this, { handleNavigationAction(it) })
    }

    private fun handleFilterToggle(item: MenuItem) {
        item.isChecked = !item.isChecked
        viewModel.filterExplicit = item.isChecked
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

    private fun initializeScrollListener() {
        binding.rvJokes.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val manager: LinearLayoutManager =
                    binding.rvJokes.layoutManager as LinearLayoutManager
                if (manager.findLastCompletelyVisibleItemPosition() == manager.itemCount - LOAD_MORE_THRESHOLD) {
                    viewModel.loadJokes()
                }
            }
        })
    }

    companion object {
        private const val LOAD_MORE_THRESHOLD = 1
    }
}
