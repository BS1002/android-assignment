package com.mahfuznow.android_assignment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahfuznow.android_assignment.model.Country
import com.mahfuznow.android_assignment.model.CountryService
import com.mahfuznow.android_assignment.model.User
import com.mahfuznow.android_assignment.model.UserService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers


class DelegateActivityViewModel : ViewModel() {
    private val countryService: CountryService = CountryService()
    val countriesLiveData: MutableLiveData<List<Country>> = MutableLiveData<List<Country>>()
    val isErrorCountryLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private val userService: UserService = UserService()
    val userLiveData: MutableLiveData<User> = MutableLiveData<User>()
    val isErrorUserLiveData: MutableLiveData<Boolean> = MutableLiveData()


    init {
        //instantiate the mutable live data
        fetchCountries()
        fetchUsers()
    }

    private fun fetchCountries() {
        countryService.countries
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                override fun onSuccess(countries: List<Country>) {
                    countriesLiveData.value = countries
                    isErrorCountryLiveData.value = false
                }
                override fun onError(e: Throwable) {
                    isErrorCountryLiveData.value = true
                }
            })
    }

    private fun fetchUsers() {
        userService.getUserResults(200)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<User>() {
                override fun onSuccess(user: User) {
                    userLiveData.value = user
                    isErrorUserLiveData.value = false
                }
                override fun onError(e: Throwable) {
                    isErrorUserLiveData.value = true
                }
            })
    }

}
