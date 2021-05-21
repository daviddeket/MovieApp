package com.example.movieApp

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.movieApp.adapters.FavoritesListAdapter
import com.example.movieApp.database.AppDatabase
import com.example.movieApp.databinding.FragmentFavoritesBinding
import com.example.movieApp.models.Movie
import com.example.movieApp.repositories.MovieRepository
import com.example.movieApp.viewmodels.MovieFavoritesViewModel
import com.example.movieApp.viewmodels.MovieFavoritesViewModelFactory

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding

    //The shorter version to get the DAO vs. the one in MovieDetailFragment
    private val viewModel: MovieFavoritesViewModel by viewModels {
        val repository = MovieRepository.getInstance(
            AppDatabase.getInstance(requireContext())!!.movieDao)
        MovieFavoritesViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        val adapter = FavoritesListAdapter(
            dataSet = listOf(),     // start with empty list
            onDeleteClicked = {movieId -> onDeleteMovieClicked(movieId)},   // pass functions to adapter
            onEditClicked = {movie -> onEditMovieClicked(movie)},           // pass functions to adapter
        )

        with(binding){
            recyclerView.adapter = adapter
        }

        //Call the observer, and if there is any changes, then update the DataSet
        viewModel.allMovies.observe(viewLifecycleOwner, Observer { favorites ->
            if(favorites != null) {
                adapter.updateDataSet(favorites)
            }
        })

        binding.clearBtn.setOnClickListener {
            viewModel.onClear()
        }

        return binding.root
    }

    // This is called when recyclerview item edit button is clicked
    private fun onEditMovieClicked(movieObj: Movie){
        // Create a builder object
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Add some Note")
        // Set up the input
        val input = EditText(context)
        // Specify the type of the input expected
        input.inputType = InputType.TYPE_CLASS_TEXT
        // Show the EditText field
        builder.setView(input)
        // Set up the buttons
        builder.setPositiveButton("OK") { _, _ ->
            // Here you get get input text from the Edittext
            movieObj.note = input.text.toString()
            // You have to call here the onUpdate method
            viewModel.onUpdate(movieObj)
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }
        builder.show()
    }

    // This is called when recyclerview item remove button is clicked
    private fun onDeleteMovieClicked(movieId: Long){
        viewModel.onDelete(movieId)
    }
}