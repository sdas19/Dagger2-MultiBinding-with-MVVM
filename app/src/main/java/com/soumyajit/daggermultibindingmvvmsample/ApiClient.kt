package com.soumyajit.daggermultibindingmvvmsample

import retrofit2.http.GET

interface ApiClient {

    @GET("/api/users?page=1")
    suspend fun getData() : DataModel
}