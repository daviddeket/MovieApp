package com.example.movieApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieApp.database.AppDatabase
import com.example.movieApp.database.MovieDao
import com.example.movieApp.databinding.FragmentMovieDetailBinding
import com.example.movieApp.models.MovieStore
import com.example.movieApp.repositories.MovieRepository
import com.example.movieApp.viewmodels.MovieFavoritesViewModel
import com.example.movieApp.viewmodels.MovieFavoritesViewModelFactory

class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding

    private lateinit var dao: MovieDao
    private lateinit var viewModelFactory: MovieFavoritesViewModelFactory
    private lateinit var viewModel: MovieFavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)

        //The longer version to get the DAO vs. in FavoritesFragment
        dao = AppDatabase.getInstance(requireContext())!!.movieDao
        viewModelFactory = MovieFavoritesViewModelFactory(MovieRepository.getInstance(dao))
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieFavoritesViewModel::class.java)

        val args = MovieDetailFragmentArgs.fromBundle(requireArguments())   // get navigation arguments

        when(val movieEntry = MovieStore().findMovieByUUID(args.movieId)){
            null -> {
                Toast.makeText(requireContext(), "Could not load movie data", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
            else -> binding.movie = movieEntry
        }

        binding.addToFavorites.setOnClickListener {
            Toast.makeText(requireContext(), "Movie saved to favorites.", Toast.LENGTH_SHORT).show()
            val movieEntry = MovieStore().findMovieByUUID(args.movieId)
            movieEntry?.let { favmovie ->  viewModel.onInsert(favmovie)}
        }

        return binding.root
    }
}