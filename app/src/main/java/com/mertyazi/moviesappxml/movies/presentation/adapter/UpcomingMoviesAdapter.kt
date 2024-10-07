package com.mertyazi.moviesappxml.movies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mertyazi.moviesappxml.core.util.Constants.IMAGE_BASE_URL
import com.mertyazi.moviesappxml.databinding.ItemUpcomingMovieBinding
import com.mertyazi.moviesappxml.movies.domain.model.Result
import com.mertyazi.moviesappxml.movies.presentation.MoviesFragment
import com.mertyazi.moviesappxml.movies.presentation.MoviesFragmentDirections

/**
 * Created by Mert Yazi
 */

class UpcomingMoviesAdapter(
    private val fragment: MoviesFragment
): RecyclerView.Adapter<UpcomingMoviesAdapter.UpcomingMoviesViewHolder>() {

    inner class UpcomingMoviesViewHolder(
        private val binding: ItemUpcomingMovieBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            Glide.with(fragment)
                .load(IMAGE_BASE_URL + result.backdropPath)
                .into(binding.ivItemUpcoming)

            binding.tvItemUpcomingTitle.text = result.title
            binding.tvItemUpcomingOverview.text = result.overview
            binding.root.setOnClickListener {
                val action = MoviesFragmentDirections
                    .actionMoviesFragmentToDetailsFragment(movieId = result.id.toString())
                fragment.findNavController().navigate(action)
            }
        }
    }

    private val differCallback = object: DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMoviesViewHolder {
        return UpcomingMoviesViewHolder(
            ItemUpcomingMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}