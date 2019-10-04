package com.soumyajit.daggermultibindingmvvmsample

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {

    @GET("/api/users?page=2")
    fun getData() : Single<DataModel>
}