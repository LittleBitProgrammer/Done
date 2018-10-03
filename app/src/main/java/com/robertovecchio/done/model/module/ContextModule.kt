package com.robertovecchio.done.model.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule constructor(private val context: Context) {

    @Provides
    fun providesContext(): Context{
        return context.applicationContext
    }

}