package com.example.assignment1.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment1.databinding.MovieActivityBinding



class MovieFragment : Fragment() {

    private val TAG = "MovieActivity"
    private lateinit var binding: MovieActivityBinding

    lateinit var viewModel: MovieViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()









    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val viewModel =
            ViewModelProvider(this).get(MovieViewModel::class.java)

        binding.recyclerview.adapter = adapter

        binding = MovieActivityBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setMovieList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {

        })
        viewModel.getAllMovies()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}



