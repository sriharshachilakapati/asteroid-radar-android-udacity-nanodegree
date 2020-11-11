package com.udacity.asteroidradar.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.data.Repository
import com.udacity.asteroidradar.data.model.Asteroid
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.ui.main.recycler.AsteroidAdapter

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels(factoryProducer = ::viewModelFactory)

    private val viewModelFactory by lazy {
        val repository = Repository(requireContext().applicationContext)

        val defaultContentDescription =
            resources.getString(R.string.this_is_nasa_s_picture_of_day_showing_nothing_yet)

        val contentDescriptionFormat =
            resources.getString(R.string.nasa_picture_of_day_content_description_format)

        MainViewModelFactory(repository, defaultContentDescription, contentDescriptionFormat)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val asteroidsAdapter = AsteroidAdapter(this::onAsteroidClicked)

        val binding = FragmentMainBinding.inflate(inflater).apply {
            lifecycleOwner = this@MainFragment
            viewModel = this@MainFragment.viewModel
            asteroidRecycler.adapter = asteroidsAdapter
        }

        viewModel.asteroids.observe(viewLifecycleOwner, asteroidsAdapter::items::set)
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun onAsteroidClicked(asteroid: Asteroid) {
        val navigateTo = MainFragmentDirections.actionShowDetail(asteroid)
        findNavController().navigate(navigateTo)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_show_week -> viewModel.setAsteroidFilter(AsteroidFilter.CURRENT_WEEK)
            R.id.filter_show_today -> viewModel.setAsteroidFilter(AsteroidFilter.ONLY_TODAY)
        }

        return true
    }
}
