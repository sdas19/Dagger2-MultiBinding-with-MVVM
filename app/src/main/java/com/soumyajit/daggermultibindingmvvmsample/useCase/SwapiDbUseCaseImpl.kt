package com.soumyajit.daggermultibindingmvvmsample.useCase

import androidx.paging.DataSource
import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeople
import com.soumyajit.daggermultibindingmvvmsample.database.StarWarsPeopleDao
import javax.inject.Inject

class SwapiDbUseCaseImpl @Inject constructor(
    private val starWarsPeopleDao: StarWarsPeopleDao
) : SwapiDbUseCase {

    override fun insertPeople(peopleList: List<StarWarsPeople>) =
        starWarsPeopleDao.insert(peopleList = peopleList)

    override fun getPeople(): DataSource.Factory<Int, StarWarsPeople> =
        starWarsPeopleDao.allPeople()
}