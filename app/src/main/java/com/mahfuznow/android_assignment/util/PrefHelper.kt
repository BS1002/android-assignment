package com.mahfuznow.android_assignment.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PrefHelper @Inject constructor(application: Application) {

    companion object {
        const val MY_PREFS_NAME = "MyPrefsFile"
    }

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE)

    fun setIsCountryCached() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isCountryCached", true)
        editor.apply()
    }

    fun getIsCountryCached(): Boolean {
        return sharedPreferences.getBoolean("isCountryCached", false)
    }

    fun setIsUserCached() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isUserCached", true)
        editor.apply()
    }

    fun getIsUserCached(): Boolean {
        return sharedPreferences.getBoolean("isUserCached", false)
    }
}