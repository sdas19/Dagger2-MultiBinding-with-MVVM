package com.soumyajit.daggermultibindingmvvmsample

import androidx.room.Entity
import androidx.room.PrimaryKey

data class DataModel(
        val page : Int,
        val per_page : Int,
        val total: Int,
        val total_pages : Int,
        val data : List<Users>
)

@Entity(tableName = "Users")
data class Users(
        @PrimaryKey val id: Int,
        val first_name:String,
        val last_name:String,
        val avatar:String
)