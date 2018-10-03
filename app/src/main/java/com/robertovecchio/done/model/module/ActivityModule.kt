package com.robertovecchio.done.model.module

import android.app.Activity
import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule constructor(private val activity: Activity) {

    @Singleton
    @Provides
    fun providesActivity(): Activity {
        return activity
    }

    @Singleton
    @Provides
    internal fun provideApplication(activity: Activity): Application {
        return activity.application
    }

}