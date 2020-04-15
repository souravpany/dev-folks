package com.android.devfolksapplication.ui.splash

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.android.devfolksapplication.ui.base.BaseViewModel
import com.android.devfolksapplication.utils.common.Event
import com.android.devfolksapplication.utils.network.NetworkHelper
import com.android.devfolksapplication.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private val splashTimeOut: Long = 3000 // 3 sec
    val launchMainActivity: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()


    override fun onCreate() {

        Handler().postDelayed({
            launchMainActivity.postValue(Event(emptyMap()))
        }, splashTimeOut)
    }
}