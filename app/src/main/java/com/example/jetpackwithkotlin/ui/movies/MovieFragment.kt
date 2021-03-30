package com.example.jetpackwithkotlin.ui.movies

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackwithkotlin.R
import com.example.jetpackwithkotlin.databinding.FragmentMovieBinding
import com.example.jetpackwithkotlin.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment: Fragment(R.layout.fragment_movie) {
    val viewModel : MovieViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val binding = FragmentMovieBinding.bind(view)

        val movieAdapter = MovieAdapter()

        binding.apply {
            recyclerViewTasks.apply {
                adapter=movieAdapter
                layoutManager= LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.movies.observe(viewLifecycleOwner){
            movieAdapter.submitList(it)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movie_menu,menu)
        val searchItem  = menu.findItem(R.id.menu_search)
        val searchview = searchItem.actionView as SearchView
        searchview.onQueryTextChanged(){
            viewModel.search.value=it
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.sort_name->{
                viewModel.sort.value = SortOrder.BY_NAME
                true
            }
            R.id.sort_dame->{
                viewModel.sort.value = SortOrder.BY_DATE
                true
            }
            R.id.hide->{
                item.isChecked = !item.isChecked
                viewModel.hide_watched.value = item.isChecked
                true
            }
            R.id.delete-> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}