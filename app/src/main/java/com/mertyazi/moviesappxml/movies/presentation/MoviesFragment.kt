package com.mertyazi.moviesappxml.movies.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.mertyazi.moviesappxml.databinding.FragmentMoviesBinding
import com.mertyazi.moviesappxml.movies.domain.model.Result
import com.mertyazi.moviesappxml.movies.presentation.adapter.PopularMoviesAdapter
import com.mertyazi.moviesappxml.movies.presentation.adapter.UpcomingMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModels()

    private lateinit var upcomingMoviesAdapter: UpcomingMoviesAdapter
    private var upcomingMoviesList: ArrayList<Result> = arrayListOf()

    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private var popularMoviesList: ArrayList<Result> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUpcomingMoviesAdapter()
        setupPopularMoviesAdapter()
        collectUpcomingMovies()
        collectPopularMovies()
    }

    private fun setupUpcomingMoviesAdapter() {
        binding.rvUpcomingMovies.setHasFixedSize(true)
        binding.rvUpcomingMovies.layoutManager = CarouselLayoutManager()
        CarouselSnapHelper().attachToRecyclerView(binding.rvUpcomingMovies)
        upcomingMoviesAdapter = UpcomingMoviesAdapter(this)
        binding.rvUpcomingMovies.adapter = upcomingMoviesAdapter
    }

    private fun setupPopularMoviesAdapter() {
        binding.rvPopularMovies.layoutManager = LinearLayoutManager(activity)
        popularMoviesAdapter = PopularMoviesAdapter(this)
        binding.rvPopularMovies.adapter = popularMoviesAdapter
    }

    private fun collectUpcomingMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.upcomingMoviesState.collect { upcomingMovies ->
                    binding.pbMoviesLoader.visibility =
                        if (upcomingMovies.loading) View.VISIBLE else View.GONE
                    upcomingMoviesList = ArrayList(upcomingMovies.upcoming.results)
                    upcomingMoviesAdapter.differ.submitList(upcomingMoviesList)
                }
            }
        }
    }

    private fun collectPopularMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.popularMoviesState.collect { popularMovies ->
                    binding.pbMoviesLoader.visibility =
                        if (popularMovies.loading) View.VISIBLE else View.GONE
                    popularMoviesList = ArrayList(popularMovies.popular.results)
                    popularMoviesAdapter.differ.submitList(popularMoviesList)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}