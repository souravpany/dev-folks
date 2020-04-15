package com.android.devfolksapplication.ui.developer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android.devfolksapplication.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.android.devfolksapplication.data.remote.response.DeveloperResponse
import com.android.devfolksapplication.di.component.ViewHolderComponent
import com.android.devfolksapplication.ui.base.BaseItemViewHolder
import com.android.devfolksapplication.utils.common.GlideHelper
import com.own.trendingapp.ui.developer.adapter.DevFolksItemViewModel
import kotlinx.android.synthetic.main.row_trending.view.*

class DevFolksItemViewHolder(
    parent: ViewGroup
) :
    BaseItemViewHolder<DeveloperResponse, DevFolksItemViewModel>(R.layout.row_trending, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }


    private val unSelected = -1
    private var selectedItem = unSelected

    override fun setupObservers() {
        super.setupObservers()

        viewModel.name.observe(this, Observer {
            itemView.txt_title.text = it
        })

        viewModel.des.observe(this, Observer {
            itemView.txt_des.text = it
        })

        viewModel.repoDes.observe(this, Observer {
            itemView.txt_title_des.text = it
        })

        viewModel.profileImage.observe(this, Observer {
            it?.run {
                val glideRequest = Glide
                    .with(itemView.img_trending.context)
                    .load(GlideHelper.getProtectedUrl(url))
                    .apply(RequestOptions.circleCropTransform())


                if (placeholderWidth > 0 && placeholderHeight > 0) {
                    val params = itemView.img_trending.layoutParams as ViewGroup.LayoutParams
                    params.width = placeholderWidth
                    params.height = placeholderHeight
                    itemView.img_trending.layoutParams = params
                    glideRequest
                        .apply(RequestOptions.overrideOf(placeholderWidth, placeholderHeight))
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_red_dot))
                }
                glideRequest.into(itemView.img_trending)
            }
        })

    }

    override fun setupView(view: View) {

        view.setOnClickListener {
            it.isSelected = false
            itemView.expandable_layout.collapse()

            val position = adapterPosition
            if (position == selectedItem) {
                selectedItem = unSelected
            } else {
                it.isSelected = true
                itemView.expandable_layout.expand()
                selectedItem = position
            }
        }
    }
}