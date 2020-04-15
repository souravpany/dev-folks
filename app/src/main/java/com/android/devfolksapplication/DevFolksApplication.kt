package com.android.devfolksapplication

import android.app.Application
import com.android.devfolksapplication.di.module.ApplicationModule
import com.android.devfolksapplication.di.component.ApplicationComponent
import com.android.devfolksapplication.di.component.DaggerApplicationComponent

class DevFolksApplication : Application() {


    lateinit var applicationComponent: ApplicationComponent


    override fun onCreate() {
        super.onCreate()
        injectDependencies()

    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }

}
