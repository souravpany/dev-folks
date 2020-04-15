package com.android.devfolksapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.android.devfolksapplication.R
import com.android.devfolksapplication.di.component.ActivityComponent
import com.android.devfolksapplication.ui.base.BaseActivity
import com.android.devfolksapplication.ui.developer.MainActivity
import com.android.devfolksapplication.utils.common.Event

class SplashActivity : BaseActivity<SplashViewModel>() {

    override fun provideLayoutId(): Int = R.layout.activity_splash_screen

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.launchMainActivity.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        })
    }
}