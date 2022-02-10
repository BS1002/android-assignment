package com.mahfuznow.android_assignment.di

import android.app.Application
import com.mahfuznow.android_assignment.repository.Repository
import com.mahfuznow.android_assignment.viewmodel.MainActivityViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, RoomModule::class])
interface DatabaseComponent {

    fun inject(repository: Repository)

    @Component.Builder
    interface Builder {
        fun build() : DatabaseComponent
        fun setApplication(@BindsInstance application: Application): Builder
    }
}