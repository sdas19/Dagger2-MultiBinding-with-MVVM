package com.soumyajit.daggermultibindingmvvmsample.activity.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeople
import com.soumyajit.daggermultibindingmvvmsample.repository.StarWarsPeopleRepository

class MainActivityViewModel @ViewModelInject constructor(
    private val starWarsPeopleRepository: StarWarsPeopleRepository
) : ViewModel() {
    private val _state : MutableLiveData<MainActivityViewState> = MutableLiveData()
    val state: LiveData<MainActivityViewState> = _state
    val list = starWarsPeopleRepository.fetchOrGetPeople()

    init {
        _state.postValue(MainActivityViewState.ShowLoading)
    }

    override fun onCleared() {
        super.onCleared()
        starWarsPeopleRepository.invalidate()
    }
}