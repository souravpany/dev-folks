package com.own.trendingapp.ui.developer.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.devfolksapplication.data.model.Image
import com.android.devfolksapplication.data.remote.response.DeveloperResponse
import com.android.devfolksapplication.utils.network.NetworkHelper
import com.android.devfolksapplication.utils.rx.SchedulerProvider
import com.android.devfolksapplication.ui.base.BaseItemViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DevFolksItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseItemViewModel<DeveloperResponse>(schedulerProvider, compositeDisposable, networkHelper) {


    val name: LiveData<String> = Transformations.map(data) { it.name }

    val des: LiveData<String> = Transformations.map(data) { it.url }

    val repoDes: LiveData<String> = Transformations.map(data) { it.repo.description }


    val profileImage: LiveData<Image> = Transformations.map(data) {
        it.imageUrl.run { this?.let { it1 -> Image(it1) } }
    }


    override fun onCreate() {

    }
}