package net.l1ngdtkh3.tmdb.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import net.l1ngdtkh3.tmdb.R
import net.l1ngdtkh3.tmdb.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment?)?.findNavController()?.let {
            NavigationUI.setupWithNavController(binding.bnv, it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            // R.id.action_favorite -> true
            // R.id.action_feed -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}