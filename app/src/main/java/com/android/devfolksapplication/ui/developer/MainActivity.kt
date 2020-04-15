package com.android.devfolksapplication.ui.developer

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.devfolksapplication.R
import com.android.devfolksapplication.di.component.ActivityComponent
import com.android.devfolksapplication.ui.base.BaseActivity
import com.own.trendingapp.ui.developer.adapter.DevFolksListAdapter
import com.android.devfolksapplication.utils.common.Resource
import com.android.devfolksapplication.utils.common.hide
import com.android.devfolksapplication.utils.common.show
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityViewModel>() {

    companion object {
        const val ANIMATION_DURATION = 1000.toLong()
    }


    override fun provideLayoutId(): Int = R.layout.activity_main

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var devFolksListAdapter: DevFolksListAdapter


    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {


        rvTrending.apply {
            layoutManager = linearLayoutManager
            adapter = devFolksListAdapter
        }


        swipeRefresh.setOnRefreshListener {
            if (viewModel.isNetworkAvailable()) {
                viewModel.onRefreshPressed(true)
                viewModel.refreshList()
            } else {
                viewModel.hideLoaders()
                viewModel.messageStringId.postValue(Resource.error(R.string.could_not_refresh))
            }
        }

    }

    override fun setupObservers() {
        super.setupObservers()


        viewModel.swipeLoading.observe(this, Observer {
            swipeRefresh.isRefreshing = it
        })


        /**
         * Observe list changes
         */
        viewModel.developerLists.observe(this, Observer {
            it.data?.run {

                if (viewModel.refreshFieldChange.value == false) {
                    devFolksListAdapter.appendData(this)
                } else {
                    devFolksListAdapter.updateList(this)
                }

            }
        })


        /**
         * Observe network changes i.e. Internet Connectivity
         */
        viewModel.isLiveNetworkAvailable().observe(this, Observer {
            if (!it) {

                textViewNetworkStatus.text = getString(R.string.text_no_connectivity)
                networkStatusLayout.apply {
                    alpha = 0f
                    show()
                    setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorStatusNotConnected
                        )
                    )
                    animate()
                        .alpha(1f)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(null)
                }
            } else {
                viewModel.onRefreshPressed(true)
                viewModel.refreshList()
                textViewNetworkStatus.text = getString(R.string.text_connectivity)
                networkStatusLayout.apply {
                    setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorStatusConnected
                        )
                    )

                    animate()
                        .alpha(0f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
            }
        })
    }
}
