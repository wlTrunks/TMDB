package net.l1ngdtkh3.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import net.l1ngdtkh3.features.feed.R
import net.l1ngdtkh3.features.feed.databinding.FragmentFeedBinding
import net.l1ngdtkh3.ui.extensions.bindingDelegate

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.fragment_feed) {

    private val binding: FragmentFeedBinding by bindingDelegate(FragmentFeedBinding::bind)

    private val viewModel: FeedViewModel by viewModels()

    private val nowPlayingAdapter = MovieAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nowPlayingRV.adapter = nowPlayingAdapter
        lifecycleScope.launchWhenCreated {
            viewModel.pagingData.collectLatest {
                nowPlayingAdapter.submitData(it)
            }
        }
    }
}