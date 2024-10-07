package com.mertyazi.moviesappxml.details.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mertyazi.moviesappxml.R
import com.mertyazi.moviesappxml.core.util.Constants.IMAGE_BASE_URL
import com.mertyazi.moviesappxml.core.util.Constants.TRAILER
import com.mertyazi.moviesappxml.core.util.Constants.dateFormatter
import com.mertyazi.moviesappxml.core.util.Constants.pointsCalculator
import com.mertyazi.moviesappxml.databinding.FragmentDetailsBinding
import com.mertyazi.moviesappxml.details.domain.model.Details
import com.mertyazi.moviesappxml.details.domain.model.Result
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    private var movie: Details = Details()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DetailsFragmentArgs by navArgs()

        collectMovieDetails()
        collectMovieVideos()

        viewModel.getMovieDetails(args.movieId)
        viewModel.getMovieVideos(args.movieId)

        binding.ivFavorite.setOnClickListener {
            viewModel.favoriteIconClicked(movie)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun collectMovieDetails() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailsState.collectLatest { movieDetails ->
                    binding.pbDetailsLoader.visibility =
                        if (movieDetails.loading) View.VISIBLE else View.GONE
                    if (movieDetails.details.title.isNotEmpty()) {
                        movie = movieDetails.details
                        Glide.with(requireContext())
                            .load(IMAGE_BASE_URL + movieDetails.details.posterPath)
                            .into(binding.ivDetails)

                        var genres = ""
                        for (genre in movieDetails.details.genres) {
                            genres += genre.name +
                                    if (genre != movieDetails.details.genres.last()) ", " else ""
                        }

                        binding.tvTitle.text = movieDetails.details.title
                        binding.tvReleaseDate.text = dateFormatter(movieDetails.details.releaseDate)
                        binding.tvGenres.text = genres
                        binding.tvVotes.text = pointsCalculator(
                            movieDetails.details.voteAverage,
                            movieDetails.details.voteCount
                        )
                        binding.tvOverview.text = movieDetails.details.overview
                        binding.rbDetails.rating = movieDetails.details.voteAverage.toFloat() / 2
                        binding.tvLanguage.text = movieDetails.details.originalLanguage.first().uppercase() +
                                movieDetails.details.originalLanguage.substring(1)

                        if (movie.isFavourite) {
                            Glide.with(requireContext())
                                .load(R.drawable.ic_favorite_selected)
                                .into(binding.ivFavorite)
                        } else {
                            Glide.with(requireContext())
                                .load(R.drawable.ic_favorite_unselected)
                                .into(binding.ivFavorite)
                        }
                    }
                }
            }
        }
    }

    private fun collectMovieVideos() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.videosState.collect { videos->
                    binding.pbDetailsLoader.visibility =
                        if (videos.loading) View.VISIBLE else View.GONE
                    if (videos.videos.results.isNotEmpty()) {
                        playYoutubeVideo(videos.videos.results)
                    }
                }
            }
        }
    }

    private fun playYoutubeVideo(results: List<Result>) {
        viewLifecycleOwner.lifecycle.addObserver(binding.player)
        binding.player.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                var videoKey = results[0].key
                for (video in results) {
                    if (video.type == TRAILER) {
                        videoKey = video.key
                    }
                }
                youTubePlayer.loadVideo(videoKey, 0f)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}