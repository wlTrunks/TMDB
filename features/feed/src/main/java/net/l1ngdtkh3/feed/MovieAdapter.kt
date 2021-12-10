package net.l1ngdtkh3.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import net.l1ngdtkh3.domain.models.Movie
import net.l1ngdtkh3.ui.databinding.ItemMovieBinding
import net.l1ngdtkh3.ui.extensions.loadImage

class MovieAdapter : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(diffItemCallback) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            with(binding) {
                feedTitleTV.text = item.title
                feedIV.load(item.posterPath?.loadImage())
                feedDateTV.text = item.releaseDate
                feedRV.setProgress(item.voteAverage)
                feedCB.isChecked = item.isFavorite
            }
        }
    }
}

private val diffItemCallback = object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id
                && oldItem.voteAverage == newItem.voteAverage
                && oldItem.isFavorite == newItem.isFavorite
}