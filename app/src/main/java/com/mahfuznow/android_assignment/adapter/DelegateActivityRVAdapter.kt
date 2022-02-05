package com.mahfuznow.android_assignment.adapter

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class DelegateActivityRVAdapter(
    context: Context
) : ListDelegationAdapter<ArrayList<Any>>() {

    init {
        // delegatesManager is a field defined in super class
        // ViewType integer is assigned internally by delegatesManager
        delegatesManager.addDelegate(CountryAdapterDelegate(context))
        delegatesManager.addDelegate(UserResultAdapterDelegate(context))
    }
}