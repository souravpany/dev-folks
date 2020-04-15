package com.android.devfolksapplication.di.component

import com.android.devfolksapplication.di.ActivityScope
import com.android.devfolksapplication.di.module.ActivityModule
import com.android.devfolksapplication.ui.developer.MainActivity
import com.android.devfolksapplication.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: SplashActivity)
}