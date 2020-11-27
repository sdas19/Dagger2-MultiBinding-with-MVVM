package com.soumyajit.daggermultibindingmvvmsample.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.soumyajit.daggermultibindingmvvmsample.R
import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeople

class StarWarsPeopleAdapter : PagedListAdapter<StarWarsPeople, StarWarsPeopleViewHolder>(StarWarsPeopleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): StarWarsPeopleViewHolder {
        return StarWarsPeopleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_single_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StarWarsPeopleViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bindTo(item)
        }
    }
}