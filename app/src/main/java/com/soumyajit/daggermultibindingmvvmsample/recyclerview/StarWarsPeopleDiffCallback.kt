package com.soumyajit.daggermultibindingmvvmsample.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeople

class StarWarsPeopleDiffCallback : DiffUtil.ItemCallback<StarWarsPeople>() {

    override fun areItemsTheSame
                (oldItem: StarWarsPeople, newItem: StarWarsPeople): Boolean
            = oldItem.name == newItem.name

    override fun areContentsTheSame
                (oldItem: StarWarsPeople, newItem: StarWarsPeople): Boolean
            = oldItem == newItem
}