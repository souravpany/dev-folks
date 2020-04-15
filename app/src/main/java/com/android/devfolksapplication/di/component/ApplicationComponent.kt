package com.android.devfolksapplication.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.android.devfolksapplication.data.remote.NetworkService
import com.android.devfolksapplication.di.ApplicationContext
import com.android.devfolksapplication.di.module.ApplicationModule
import com.android.devfolksapplication.DevFolksApplication
import com.android.devfolksapplication.utils.network.NetworkHelper
import com.android.devfolksapplication.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: DevFolksApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context


    fun getNetworkService(): NetworkService


    fun getSharedPreferences(): SharedPreferences

    fun getNetworkHelper(): NetworkHelper

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable
}