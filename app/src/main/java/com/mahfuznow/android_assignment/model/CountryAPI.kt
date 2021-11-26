package com.mahfuznow.android_assignment.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CountryAPI {
    @get:GET("all")
    val countries: Single<List<Country>>
}
