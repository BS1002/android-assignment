package com.mahfuznow.android_assignment.repository

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.mahfuznow.android_assignment.model.country.Country
import com.mahfuznow.android_assignment.model.user.User
import com.mahfuznow.android_assignment.repository.remote.CountryApiService
import com.mahfuznow.android_assignment.repository.local.LocalDatabase
import com.mahfuznow.android_assignment.repository.remote.UserApiService
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
    private val userDao = localDatabase.getUserDao()

    //Remote Database
    private val countryApiService = CountryApiService()
    private val userApiService = UserApiService()

    //Shared Preference
    private val prefHelper = PrefHelper(application)

    fun getCountries(): Single<List<Country>> {
        return if (prefHelper.getIsCountryCached()) {
            Log.d("TAG", "getCountries: Loading from Local Database")
            countryDao.getAllCountries()
        } else{
            Log.d("TAG", "getCountries: Loading from Remote Database")
            countryApiService.countries
        }
    }

    //Storing remotely fetched country data into local database
    fun storeCountriesLocally(countries: List<Country>) {
        if (!prefHelper.getIsCountryCached()) {
            Observable.fromCallable {
                Runnable {
                    countryDao.deleteAllCountries()
                    countryDao.insertAllCountries(*countries.toTypedArray())
                }.run()
            }
                .subscribeOn(Schedulers.io())
                .subscribe {
                    Log.d("TAG", "Successfully saved Country's data into Local Database")
                    prefHelper.setIsCountryCached()
                }
        }

    }

    fun getUsers(): Single<User> {
        return if (prefHelper.getIsCountryCached()) {
            Log.d("TAG", "getUsers: Loading from Local Database")
            userDao.getUserResponse()
        } else{
            Log.d("TAG", "getUsers: Loading from Remote Database")
            userApiService.getUserResponse(100)
        }
    }

    //Storing remotely fetched country data into local database
    fun storeUsersLocally(user: User) {
        if (!prefHelper.getIsUserCached()) {
            Observable.fromCallable {
                Runnable {
                    userDao.deleteAllUserResponse()
                    userDao.insertUserResponse(user)
                }.run()
            }
                .subscribeOn(Schedulers.io())
                .subscribe {
                    Log.d("TAG", "Successfully saved User's data into Local Database")
                    prefHelper.setIsUserCached()
                }
        }
    }


}