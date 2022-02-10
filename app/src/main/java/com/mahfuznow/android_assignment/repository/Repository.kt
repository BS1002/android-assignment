package com.mahfuznow.android_assignment.repository

import android.app.Application
import android.util.Log
import com.mahfuznow.android_assignment.di.DaggerDatabaseComponent
import com.mahfuznow.android_assignment.model.country.Country
import com.mahfuznow.android_assignment.model.user.User
import com.mahfuznow.android_assignment.repository.local.CountryDao
import com.mahfuznow.android_assignment.repository.local.UserDao
import com.mahfuznow.android_assignment.repository.remote.CountryApi
import com.mahfuznow.android_assignment.repository.remote.UserApi
import com.mahfuznow.android_assignment.util.PrefHelper
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class Repository(application: Application) {

    init {
        val databaseComponent = DaggerDatabaseComponent.factory().create(application)
        databaseComponent.inject(this)
    }

    //Local Database
    @Inject
    lateinit var countryDao: CountryDao

    @Inject
    lateinit var userDao: UserDao


    //Remote Database
    @Inject
    lateinit var countryApi: CountryApi

    @Inject
    lateinit var userApi: UserApi

    //Shared Preference
    private val prefHelper = PrefHelper(application)


    fun getCountries(): Single<List<Country>> {
        return if (prefHelper.getIsCountryCached()) {
            Log.d("TAG", "getCountries: Loading from Local Database")
            countryDao.getAllCountries()
        } else {
            Log.d("TAG", "getCountries: Loading from Remote Database")
            countryApi.countries
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
        } else {
            Log.d("TAG", "getUsers: Loading from Remote Database")
            userApi.getUserResponse(100)
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