package com.mahfuznow.android_assignment.repository.remote

import com.mahfuznow.android_assignment.model.country.Country
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CountryApi {
    @get:GET("all")
    val countries: Single<List<Country>>
}
