package com.mahfuznow.android_assignment.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mahfuznow.android_assignment.model.country.Country
import com.mahfuznow.android_assignment.model.user.User

@Database(entities = [Country::class, User::class], version = 1,exportSchema = false)
@TypeConverters(User.Converters::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun getCountryDao(): CountryDao
    abstract fun getUserDao(): UserDao
}