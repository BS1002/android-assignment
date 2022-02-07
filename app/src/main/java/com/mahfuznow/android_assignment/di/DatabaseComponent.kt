package com.mahfuznow.android_assignment.di

import com.mahfuznow.android_assignment.repository.Repository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, RoomModule::class])
interface DatabaseComponent {
    fun inject(repository: Repository)
}