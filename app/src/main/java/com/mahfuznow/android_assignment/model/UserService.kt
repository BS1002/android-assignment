package com.mahfuznow.android_assignment.model

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UserService : UserAPI {
    private var api: UserAPI

    // As we are implementing the CountryAPI interface here, we need to define its functions.
    override fun getUserResults(numberOfUser: Int): Single<User> {
        return api.getUserResults(numberOfUser)
    }

    companion object {
        const val BASE_URL = "https://randomuser.me/";
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        api = retrofit.create(UserAPI::class.java)
    }
}
