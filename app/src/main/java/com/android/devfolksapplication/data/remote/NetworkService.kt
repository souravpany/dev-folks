package com.android.devfolksapplication.data.remote

import com.android.devfolksapplication.data.remote.response.DeveloperResponse
import io.reactivex.Single
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {


    @GET(Endpoints.DEVELOPERLIST)
    fun doDeveloperListCall(
    ): Single<List<DeveloperResponse>>

}