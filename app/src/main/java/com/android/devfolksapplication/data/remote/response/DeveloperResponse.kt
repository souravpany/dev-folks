package com.android.devfolksapplication.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DeveloperResponse(

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("avatar")
    val imageUrl: String?,

    @Expose
    @SerializedName("url")
    val url: String?,

    @Expose
    @SerializedName("repo")
    val repo: Repo
) {

    data class Repo(
        @Expose
        @SerializedName("name")
        val name: String,

        @Expose
        @SerializedName("description")
        val description: String,

        @Expose
        @SerializedName("url")
        val url: String?
    )
}