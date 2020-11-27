package com.soumyajit.daggermultibindingmvvmsample.useCase

import com.soumyajit.daggermultibindingmvvmsample.Constants.PEOPLE_API_PATH
import com.soumyajit.daggermultibindingmvvmsample.data.StarWarsPeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SwapiApiClient {

    @GET(PEOPLE_API_PATH)
    suspend fun getPeople(@Query ("page") page: Int) : StarWarsPeopleResponse
}