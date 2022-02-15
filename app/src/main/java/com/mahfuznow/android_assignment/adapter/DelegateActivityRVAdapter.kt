package com.mahfuznow.android_assignment.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import javax.inject.Inject

class DelegateActivityRVAdapter@Inject constructor(
    countryAdapterDelegate: CountryAdapterDelegate,
    userResultAdapterDelegate: UserResultAdapterDelegate
) : ListDelegationAdapter<ArrayList<Any>>() {

    init {
        // delegatesManager is a field defined in super class
        // ViewType integer is assigned internally by delegatesManager
        delegatesManager.addDelegate(countryAdapterDelegate)
        delegatesManager.addDelegate(userResultAdapterDelegate)
    }
}