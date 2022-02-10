package com.mahfuznow.android_assignment.di

import com.mahfuznow.android_assignment.repository.remote.CountryApi
import com.mahfuznow.android_assignment.repository.remote.UserApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
object RetrofitModule {

    private const val COUNTRY_BASE_URL = "https://restcountries.com/v2/"
    private const val USER_BASE_URL = "https://randomuser.me/"

    @Singleton
    @Provides
    @Named("country")
    fun getCountryRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(COUNTRY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getCountryApi(@Named("country") countryRetrofit: Retrofit): CountryApi {
        return countryRetrofit.create(CountryApi::class.java)
    }

    @Singleton
    @Provides
    @Named("user")
    fun getUserRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(USER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getUserApi(@Named("user") userRetrofit: Retrofit): UserApi {
        return userRetrofit.create(UserApi::class.java)
    }
}