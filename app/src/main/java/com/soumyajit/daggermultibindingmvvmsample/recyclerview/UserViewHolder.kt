package com.soumyajit.daggermultibindingmvvmsample.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.soumyajit.daggermultibindingmvvmsample.R
import com.soumyajit.daggermultibindingmvvmsample.Users
import kotlinx.android.synthetic.main.layout_single_item.view.*

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    
    fun bindTo(user: Users){
        itemView.name.text = user.first_name
        Glide
            .with(itemView)
            .load(user.avatar)
            .centerCrop()
            .placeholder(R.drawable.user_avatar)
            .into(itemView.image);
    }
}