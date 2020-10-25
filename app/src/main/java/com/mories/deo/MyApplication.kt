package com.mories.deo

import android.app.Application
import com.mories.core.di.CoreComponent
import com.mories.core.di.DaggerCoreComponent
import com.mories.deo.di.AppComponent
import com.mories.deo.di.DaggerAppComponent

open class MyApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}