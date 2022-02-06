package com.mahfuznow.android_assignment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahfuznow.android_assignment.model.Country
import com.mahfuznow.android_assignment.model.CountryService
import com.mahfuznow.android_assignment.model.User
import com.mahfuznow.android_assignment.model.userdata.Result
import com.mahfuznow.android_assignment.model.UserService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers


class DelegateActivityViewModel : ViewModel() {
    private val countryService: CountryService = CountryService()
    private var countries: List<Country> = ArrayList()
    var isErrorCountryLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private val userService: UserService = UserService()
    private var userResults: List<Result> = ArrayList()
    var isErrorUserLiveData: MutableLiveData<Boolean> = MutableLiveData()

    var listItems: MutableLiveData<ArrayList<Any>> = MutableLiveData()

    private var isLoadCountry = true
    private var isLoadUser = true

    fun fetchData(isLoadCountry: Boolean,isLoadUser:Boolean) {
        this.isLoadCountry = isLoadCountry
        this.isLoadUser = isLoadUser

        if (isLoadCountry)
            fetchCountries()
        if(isLoadUser)
            fetchUsers()
        if(!isLoadCountry && !isLoadUser)
            listItems.value = ArrayList()
    }

    fun reFetchData(isLoadCountry: Boolean,isLoadUser:Boolean) {
        isErrorCountryLiveData = MutableLiveData()
        isErrorUserLiveData = MutableLiveData()
        countries = emptyList()
        userResults = emptyList()
        fetchData(isLoadCountry,isLoadUser)
    }

    private fun fetchCountries() {
        countryService.countries
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                override fun onSuccess(items: List<Country>) {
                    countries = items
                    isErrorCountryLiveData.value = false
                    updateListItems()
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
                    userResults = user.results
                    isErrorUserLiveData.value = false
                    updateListItems()
                }
                override fun onError(e: Throwable) {
                    isErrorUserLiveData.value = true
                }
            })
    }

    private fun updateListItems() {
        val mergedList: ArrayList<Any> = ArrayList()
        if(isLoadCountry && isLoadUser) {
            if(isErrorCountryLiveData.value == false && isErrorUserLiveData.value == false) {
                mergedList.addAll(countries)
                mergedList.addAll(userResults)
                mergedList.shuffle()
                listItems.value = mergedList
                Log.d("TAG", "updateListItems: ")
            }
        }
        else if (isLoadCountry){
            mergedList.addAll(countries)
            mergedList.shuffle()
            listItems.value = mergedList
        }
        else if (isLoadUser) {
            mergedList.addAll(userResults)
            mergedList.shuffle()
            listItems.value = mergedList
        }
    }
}
