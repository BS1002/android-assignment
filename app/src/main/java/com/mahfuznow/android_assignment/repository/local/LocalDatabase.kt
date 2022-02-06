package com.mahfuznow.android_assignment.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahfuznow.android_assignment.model.country.Country

@Database(entities = [Country::class], version = 1,exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getCountryDao(): CountryDao
}