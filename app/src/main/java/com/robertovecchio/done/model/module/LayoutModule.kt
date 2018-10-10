package com.robertovecchio.done.model.module

import com.robertovecchio.done.view.anko.add.AddLayout
import com.robertovecchio.done.view.anko.main.HomeLayout
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LayoutModule {
    @Singleton
    @Provides
    fun provideHomeLayout(): HomeLayout{
        return HomeLayout()
    }

    @Singleton
    @Provides
    fun provideAddLayout(): AddLayout{
        return AddLayout()
    }
}