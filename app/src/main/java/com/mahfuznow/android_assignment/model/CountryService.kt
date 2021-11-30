package com.mahfuznow.android_assignment.model

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryService : CountryAPI {
    private var api: CountryAPI

    // As we are implementing the CountryAPI interface here, we need to define its functions.
    override val countries: Single<List<Country>> get() = api.countries

    companion object {
        //const val BASE_URL = "https://mahfuznow.com/api/countries/"
        //public static final String BASE_URL = "https://restcountries.eu/rest/v2/";
        const val BASE_URL = "https://restcountries.com/v2/";
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
