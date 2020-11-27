package com.soumyajit.daggermultibindingmvvmsample.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeople

interface StarWarsPeopleRepository {
    fun fetchOrGetPeople(): LiveData<PagedList<StarWarsPeople>>
    fun invalidate()
}