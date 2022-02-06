package com.mahfuznow.android_assignment.repository

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.mahfuznow.android_assignment.model.country.Country
import com.mahfuznow.android_assignment.repository.remote.CountryApiService
import com.mahfuznow.android_assignment.repository.local.LocalDatabase
import com.mahfuznow.android_assignment.util.PrefHelper
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class Repository(application: Application) {

    //Local Database
    private val localDatabase =
        Room.databaseBuilder(application, LocalDatabase::class.java, "my-app")
            .fallbackToDestructiveMigration()
            .build()
    private val countryDao = localDatabase.getCountryDao()

    //Remote Database
    private val countryApiService = CountryApiService()

    //Shared Preference
    private val prefHelper = PrefHelper(application)

    fun getCountries(): Single<List<Country>> {
        return if (prefHelper.getIsCountryCached())
            countryDao.getAllCountries()
        else
            countryApiService.countries
    }

    //Storing remotely fetched country data into local database
    fun storeCountriesLocally(countries: List<Country>) {
        Observable.fromCallable {
            Runnable {
                countryDao.deleteAllCountries()
                countryDao.insertAllCountries(*countries.toTypedArray())
            }.run()
        }
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.d("TAG", "Successfully saved data into Local Database")
                prefHelper.setIsCountryCached()
            }
    }

}