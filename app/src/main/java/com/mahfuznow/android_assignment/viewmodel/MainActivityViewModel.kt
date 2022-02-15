package com.mahfuznow.android_assignment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahfuznow.android_assignment.model.country.Country
import com.mahfuznow.android_assignment.model.user.Result
import com.mahfuznow.android_assignment.model.user.User
import com.mahfuznow.android_assignment.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private var countries: List<Country> = ArrayList()
    private var userResults: List<Result> = ArrayList()

    //Observables
    var listItems: MutableLiveData<ArrayList<Any>> = MutableLiveData()
    var isErrorCountryLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var isErrorUserLiveData: MutableLiveData<Boolean> = MutableLiveData()

    var isLoadCountry = true
    var isLoadUser = true

    init {
        fetchData(isLoadCountry, isLoadUser)
    }

    private fun fetchData(isLoadCountry: Boolean, isLoadUser: Boolean) {
        this.isLoadCountry = isLoadCountry
        this.isLoadUser = isLoadUser

        if (isLoadCountry)
            fetchCountries()
        if (isLoadUser)
            fetchUsers()
        if (!isLoadCountry && !isLoadUser)
            listItems.value = ArrayList() //Empty
    }

    fun reFetchData(isLoadCountry: Boolean, isLoadUser: Boolean) {
        isErrorCountryLiveData = MutableLiveData()
        isErrorUserLiveData = MutableLiveData()
        countries = emptyList()
        userResults = emptyList()
        fetchData(isLoadCountry, isLoadUser)
    }

    private fun fetchCountries() {
        repository.getCountries()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                override fun onSuccess(items: List<Country>) {
                    Log.d("TAG", "fetchCountries: Loaded Successfully")
                    countries = items
                    isErrorCountryLiveData.value = false
                    updateListItemsObservable()
                    repository.storeCountriesLocally(countries)
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG", "fetchCountries: Failed")
                    isErrorCountryLiveData.value = true
                }
            })
    }

    private fun fetchUsers() {
        repository.getUsers()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<User>() {
                override fun onSuccess(user: User) {
                    Log.d("TAG", "fetchUsers: Loaded Successfully")
                    userResults = user.results
                    isErrorUserLiveData.value = false
                    updateListItemsObservable()
                    repository.storeUsersLocally(user)
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG", "fetchUsers: Failed")
                    isErrorUserLiveData.value = true
                }
            })
    }

    private fun updateListItemsObservable() {
        Log.d("TAG", "updateListItemsObservable: ")
        val mergedList: ArrayList<Any> = ArrayList()
        if (isLoadCountry && isLoadUser) {
            if (isErrorCountryLiveData.value == false && isErrorUserLiveData.value == false) {
                mergedList.addAll(countries)
                mergedList.addAll(userResults)
                mergedList.shuffle()
                listItems.value = mergedList
            }
        } else if (isLoadCountry) {
            mergedList.addAll(countries)
            mergedList.shuffle()
            listItems.value = mergedList
        } else if (isLoadUser) {
            mergedList.addAll(userResults)
            mergedList.shuffle()
            listItems.value = mergedList
        }
    }
}
