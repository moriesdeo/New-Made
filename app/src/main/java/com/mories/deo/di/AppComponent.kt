package com.mories.deo.di

import com.mories.core.di.CoreComponent
import com.mories.deo.MainActivity
import com.mories.deo.ui.DetailActivity
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(detailActivity: DetailActivity)
}