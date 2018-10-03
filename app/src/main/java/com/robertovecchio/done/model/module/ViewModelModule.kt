package com.robertovecchio.done.model.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robertovecchio.done.model.factory.ViewModelFactory
import com.robertovecchio.done.model.key.ViewModelKey
import com.robertovecchio.done.viewmodel.AddViewModel
import com.robertovecchio.done.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module(includes = [ActivityModule::class])
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    @Singleton
    internal abstract fun provideHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddViewModel::class)
    @Singleton
    internal abstract fun provideAddViewModel(viewModel: AddViewModel): ViewModel


}