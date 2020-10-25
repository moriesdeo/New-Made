package com.mories.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mories.core.ui.ViewModelFactory
import com.mories.deo.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelFavoriteModule {
    @Binds
    @IntoMap
    @ViewModelKey(Favorite2ViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: Favorite2ViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}