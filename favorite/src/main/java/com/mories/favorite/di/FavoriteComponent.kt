package com.mories.favorite.di

import com.mories.core.di.CoreComponent
import com.mories.deo.di.AppModule
import com.mories.deo.di.AppScope
import com.mories.favorite.FavoriteActivity
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelFavoriteModule::class]
)
interface FavoriteComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): FavoriteComponent
    }

    fun inject(favoriteActivity: FavoriteActivity)
}