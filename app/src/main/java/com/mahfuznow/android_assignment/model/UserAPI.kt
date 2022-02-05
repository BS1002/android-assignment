package com.mahfuznow.android_assignment.model

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPI {
    @GET("api")
    fun getUserResults(@Query("results") numberOfUser: Int): Single<User>
}
