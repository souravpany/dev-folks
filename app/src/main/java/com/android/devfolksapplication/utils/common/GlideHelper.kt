package com.android.devfolksapplication.utils.common

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

object GlideHelper {

    fun getProtectedUrl(url: String): GlideUrl {
        val builder = LazyHeaders.Builder()
        return GlideUrl(url, builder.build())
    }
}
