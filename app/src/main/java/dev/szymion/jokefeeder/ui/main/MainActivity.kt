package dev.szymion.jokefeeder.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.szymion.jokefeeder.R
import dev.szymion.jokefeeder.databinding.ActivityMainBinding
import dev.szymion.jokefeeder.ui.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
        initializeToolbar()
    }

    private fun initializeBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.model = viewModel
    }

    private fun initializeToolbar() {
        setSupportActionBar(binding.toolbar)
        toolbar.setupWithNavController(findNavController())
    }

    private fun findNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }
}
