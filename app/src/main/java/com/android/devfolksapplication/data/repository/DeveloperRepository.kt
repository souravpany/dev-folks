package com.android.devfolksapplication.data.repository

import com.android.devfolksapplication.data.remote.NetworkService
import com.android.devfolksapplication.data.remote.response.DeveloperResponse
import io.reactivex.Single
import javax.inject.Inject

class DeveloperRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun fetchTreadingList(): Single<List<DeveloperResponse>> =
        networkService.doDeveloperListCall().map {
            it
        }

}