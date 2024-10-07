package com.mertyazi.moviesappxml.favorites.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.mertyazi.moviesappxml.databinding.FragmentFavoritesBinding
import com.mertyazi.moviesappxml.favorites.presentation.adapter.FavoriteMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModels()

    private lateinit var favoriteMoviesAdapter: FavoriteMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFavoriteMoviesAdapter()
        collectFavoriteMovies()
        viewModel.getFavoriteMovies()
    }

    private fun collectFavoriteMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoritesState.collectLatest { favorites ->
                    binding.pbFavoritesLoader.visibility =
                        if (favorites.loading) View.VISIBLE else View.GONE
                    favoriteMoviesAdapter.differ.submitList(favorites.favorites)
                }
            }
        }
    }

    private fun setupFavoriteMoviesAdapter() {
        binding.rvFavoriteMovies.layoutManager = GridLayoutManager(activity, 2)
        favoriteMoviesAdapter = FavoriteMoviesAdapter(this)
        binding.rvFavoriteMovies.adapter = favoriteMoviesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}