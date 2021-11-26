package com.mahfuznow.android_assignment.model

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryService : CountryAPI {
    var api: CountryAPI

    // As we are implementing the CountryAPI interface here, we need to define its functions.
    override val countries: Single<List<Country>>
        get() = api.countries

    companion object {
        //public static final String BASE_URL = "https://restcountries.eu/rest/v2/";
        const val BASE_URL = "https://mahfuznow.com/api/countries/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        api = retrofit.create(CountryAPI::class.java)
    }
}
