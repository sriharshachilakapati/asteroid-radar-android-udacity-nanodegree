package com.udacity.asteroidradar.ui.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.data.model.Asteroid
import com.udacity.asteroidradar.databinding.LayoutAsteroidItemBinding

class AsteroidViewHolder private constructor(private val binding: LayoutAsteroidItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): AsteroidViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LayoutAsteroidItemBinding.inflate(inflater, parent, false)

            return AsteroidViewHolder(binding)
        }
    }

    fun bind(asteroid: Asteroid, itemClickHandler: ItemClickHandler) = with(binding) {
        this.asteroid = asteroid
        root.setOnClickListener { itemClickHandler(asteroid) }
        executePendingBindings()
    }
}
