package com.soumyajit.daggermultibindingmvvmsample.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.soumyajit.daggermultibindingmvvmsample.Constants.STARWARS_PEOPLE

@Entity(tableName = STARWARS_PEOPLE)
data class StarWarsPeople(
    @PrimaryKey
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)