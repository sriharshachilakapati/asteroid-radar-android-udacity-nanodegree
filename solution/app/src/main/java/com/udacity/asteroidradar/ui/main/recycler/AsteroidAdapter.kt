package com.udacity.asteroidradar.ui.main.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.data.model.Asteroid

typealias ItemClickHandler = (Asteroid) -> Unit

class AsteroidAdapter(private val itemClickHandler: ItemClickHandler) :
    RecyclerView.Adapter<AsteroidViewHolder>() {

    private val diff = AsyncListDiffer(this, AsteroidDiffCallback())

    var items: List<Asteroid> = emptyList()
        set(value) {
            field = value
            diff.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AsteroidViewHolder.create(parent)

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) =
        holder.bind(items[position], itemClickHandler)

    override fun getItemCount(): Int = items.size
}