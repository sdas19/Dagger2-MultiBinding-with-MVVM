package com.soumyajit.daggermultibindingmvvmsample.useCase

import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeopleResponse
import javax.inject.Inject

class SwapiRemoteUseCaseImpl @Inject constructor(
    private val swapiApiClient: SwapiApiClient
) : SwapiRemoteUseCase {

    override suspend fun getPeople(pageNo: Int): StarWarsPeopleResponse =
        swapiApiClient.getPeople(page = pageNo)
}