package com.soumyajit.daggermultibindingmvvmsample.data

import com.google.gson.annotations.SerializedName

data class StarWarsPeopleResponse (
    @SerializedName("results")
    val results: List<StarWarsPeople>,
)