package com.soumyajit.daggermultibindingmvvmsample.useCase

import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeopleResponse

interface SwapiRemoteUseCase {

    suspend fun getPeople(pageNo: Int): StarWarsPeopleResponse
}