package com.udacity.asteroidradar.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels(factoryProducer = ::viewModelFactory)

    private val viewModelFactory by lazy {
        val defaultContentDescription =
            resources.getString(R.string.this_is_nasa_s_picture_of_day_showing_nothing_yet)

        val contentDescriptionFormat =
            resources.getString(R.string.nasa_picture_of_day_content_description_format)

        MainViewModelFactory(defaultContentDescription, contentDescriptionFormat)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater).apply {
            lifecycleOwner = this@MainFragment
            viewModel = this@MainFragment.viewModel
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
