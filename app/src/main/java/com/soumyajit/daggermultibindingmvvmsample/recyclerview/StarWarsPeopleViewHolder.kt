package com.soumyajit.daggermultibindingmvvmsample.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeople
import kotlinx.android.synthetic.main.layout_single_item.view.*

class StarWarsPeopleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    
    fun bindTo(user: StarWarsPeople){
        itemView.name.text = user.name
    }
}