package com.soumyajit.daggermultibindingmvvmsample.useCase

import androidx.paging.DataSource
import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeople

interface SwapiDbUseCase {

    fun insertPeople(peopleList: List<StarWarsPeople>)
    fun getPeople() : DataSource.Factory<Int, StarWarsPeople>
}