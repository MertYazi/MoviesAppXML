package com.mertyazi.moviesappxml.favorites.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mertyazi.moviesappxml.core.domain.model.FavoriteMovie
import com.mertyazi.moviesappxml.core.util.Constants.IMAGE_BASE_URL
import com.mertyazi.moviesappxml.databinding.ItemFavoriteMovieBinding
import com.mertyazi.moviesappxml.favorites.presentation.FavoritesFragment
import com.mertyazi.moviesappxml.favorites.presentation.FavoritesFragmentDirections

/**
 * Created by Mert Yazi
 */

class FavoriteMoviesAdapter(
    private val fragment: FavoritesFragment
): RecyclerView.Adapter<FavoriteMoviesAdapter.FavoriteMoviesViewHolder>() {

    inner class FavoriteMoviesViewHolder(
        private val binding: ItemFavoriteMovieBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: FavoriteMovie) {
            Glide.with(fragment)
                .load(IMAGE_BASE_URL + favorite.moviePosterWithTitle)
                .into(binding.ivItemFavorite)

            binding.tvItemFavoriteTitle.text = favorite.movieTitle
            binding.root.setOnClickListener {
                val action = FavoritesFragmentDirections
                    .actionFavoritesFragmentToDetailsFragment(movieId = favorite.id)
                fragment.findNavController().navigate(action)
            }
        }
    }

    private val differCallback = object: DiffUtil.ItemCallback<FavoriteMovie>() {
        override fun areItemsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMoviesViewHolder {
        return FavoriteMoviesViewHolder(
            ItemFavoriteMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}