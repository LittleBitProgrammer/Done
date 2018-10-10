package com.robertovecchio.done.model.module

import com.robertovecchio.done.view.fragment.add.AddFragment
import com.robertovecchio.done.view.fragment.main.HomeFragment
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FragmentModule{

    @Singleton
    @Provides
    fun provideHomeFragment(): HomeFragment {
        return HomeFragment()
    }

    @Singleton
    @Provides
    fun provideAddFragment(): AddFragment {
        return AddFragment()
    }
}