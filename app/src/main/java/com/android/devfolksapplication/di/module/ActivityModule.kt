package com.android.devfolksapplication.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.devfolksapplication.data.repository.DeveloperRepository
import com.android.devfolksapplication.ui.base.BaseActivity
import com.android.devfolksapplication.ui.developer.MainActivityViewModel
import com.own.trendingapp.ui.developer.adapter.DevFolksListAdapter
import com.android.devfolksapplication.ui.splash.SplashViewModel
import com.android.devfolksapplication.utils.ViewModelProviderFactory
import com.android.devfolksapplication.utils.network.NetworkHelper
import com.android.devfolksapplication.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideTrendingListAdapter() = DevFolksListAdapter(activity.lifecycle, ArrayList())


    @Provides
    fun provideSplashViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): SplashViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(SplashViewModel::class) {
            SplashViewModel(schedulerProvider, compositeDisposable, networkHelper)
            //this lambda creates and return SplashViewModel
        }).get(SplashViewModel::class.java)


    @Provides
    fun provideMainActivityViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        developerRepository: DeveloperRepository
    ): MainActivityViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainActivityViewModel::class) {
            MainActivityViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                ArrayList(),
                developerRepository
            )
            //this lambda creates and return MainActivityViewModel
        }).get(MainActivityViewModel::class.java)
}