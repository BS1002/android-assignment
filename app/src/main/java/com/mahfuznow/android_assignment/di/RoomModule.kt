package com.mahfuznow.android_assignment.di

import android.app.Application
import androidx.room.Room
import com.mahfuznow.android_assignment.repository.local.CountryDao
import com.mahfuznow.android_assignment.repository.local.RoomDb
import com.mahfuznow.android_assignment.repository.local.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {

    private var roomDb: RoomDb = Room.databaseBuilder(application, RoomDb::class.java, "my-app")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun getRoomInstance(): RoomDb {
        return roomDb
    }

    @Singleton
    @Provides
    fun gerCountryDao(): CountryDao {
        return roomDb.getCountryDao()
    }

    @Singleton
    @Provides
    fun gerUserDao(): UserDao {
        return roomDb.getUserDao()
    }
}