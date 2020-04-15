package com.own.trendingapp.ui.developer.adapter

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.android.devfolksapplication.data.remote.response.DeveloperResponse
import com.android.devfolksapplication.ui.base.BaseAdapter
import com.android.devfolksapplication.ui.developer.adapter.DevFolksItemViewHolder

class DevFolksListAdapter(
    parentLifecycle: Lifecycle,
    trending: ArrayList<DeveloperResponse>
) : BaseAdapter<DeveloperResponse, DevFolksItemViewHolder>(parentLifecycle, trending) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DevFolksItemViewHolder(parent)
}