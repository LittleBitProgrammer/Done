package com.robertovecchio.done.model.component

import com.robertovecchio.done.model.module.AnkoModule
import com.robertovecchio.done.model.module.ViewModelModule
import com.robertovecchio.done.view.fragment.add.AddFragment
import com.robertovecchio.done.view.fragment.main.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AnkoModule::class, ViewModelModule::class])
interface DoneComponent {
    fun injectHome(fragment: HomeFragment)
    fun injectAdd(fragment: AddFragment)
}