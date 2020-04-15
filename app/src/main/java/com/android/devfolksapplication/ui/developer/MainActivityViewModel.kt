package com.android.devfolksapplication.ui.developer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.devfolksapplication.data.remote.response.DeveloperResponse
import com.android.devfolksapplication.data.repository.DeveloperRepository
import com.android.devfolksapplication.ui.base.BaseViewModel
import com.android.devfolksapplication.utils.common.Resource
import com.android.devfolksapplication.utils.network.NetworkHelper
import com.android.devfolksapplication.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val trendingList: ArrayList<DeveloperResponse>,
    private val trendingRepository: DeveloperRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {


    val developerLists: MutableLiveData<Resource<List<DeveloperResponse>>> = MutableLiveData()
    val swipeLoading: MutableLiveData<Boolean> = MutableLiveData()
    val refreshFieldChange: MutableLiveData<Boolean> = MutableLiveData(false)


    fun isNetworkAvailable(): Boolean = networkHelper.isNetworkConnected()

    fun isLiveNetworkAvailable(): LiveData<Boolean> = networkHelper.getNetworkLiveData()


    fun onRefreshPressed(status: Boolean) = refreshFieldChange.postValue(status)


    init {
        refreshList()
    }

    override fun onCreate() {}

    fun refreshList() {
        showLoaders()
        compositeDisposable.addAll(
            trendingRepository.fetchTreadingList()
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        trendingList.addAll(it)
                        hideLoaders()
                        developerLists.postValue(Resource.success(it))
                    },
                    {
                        handleNetworkError(it)
                        hideLoaders()
                    }

                )

        )
    }

    fun hideLoaders() {
        swipeLoading.postValue(false)
    }

    private fun showLoaders() {
        swipeLoading.postValue(true)
    }
}
