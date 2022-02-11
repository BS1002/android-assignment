package com.mahfuznow.android_assignment.di

import android.app.Application
import androidx.room.Room
import com.mahfuznow.android_assignment.repository.local.CountryDao
import com.mahfuznow.android_assignment.repository.local.RoomDb
import com.mahfuznow.android_assignment.repository.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun getRoomInstance(application: Application): RoomDb {
        return Room.databaseBuilder(application, RoomDb::class.java, "my-app")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun gerCountryDao(roomDb: RoomDb): CountryDao {
        return roomDb.getCountryDao()
    }

    @Singleton
    @Provides
    fun gerUserDao(roomDb: RoomDb): UserDao {
        return roomDb.getUserDao()
    }
}