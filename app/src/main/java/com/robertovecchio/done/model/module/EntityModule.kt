package com.robertovecchio.done.model.module

import com.robertovecchio.done.model.entity.Tag
import com.robertovecchio.done.model.entity.Task
import com.robertovecchio.done.model.entity.User
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EntityModule {

    @Singleton
    @Provides
    fun provideTag(): Tag{
        return Tag()
    }

    @Singleton
    @Provides
    fun provideTask(): Task{
        return Task()
    }

    @Singleton
    @Provides
    fun provideUser(): User{
        return User()
    }

}