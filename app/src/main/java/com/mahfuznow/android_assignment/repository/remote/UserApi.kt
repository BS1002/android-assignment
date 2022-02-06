package com.mahfuznow.android_assignment.repository.remote

import com.mahfuznow.android_assignment.model.user.User
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("api")
    fun getUserResults(@Query("results") numberOfUser: Int): Single<User>
}
