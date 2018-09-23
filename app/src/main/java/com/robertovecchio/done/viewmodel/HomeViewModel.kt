package com.robertovecchio.done.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.robertovecchio.done.model.entity.Tag
import com.robertovecchio.done.model.entity.Task
import com.robertovecchio.done.model.entity.User
import com.robertovecchio.done.model.repository.RepositoryApi

class HomeViewModel constructor(application: Application): AndroidViewModel(application) {

    private val mRepositoryApi: RepositoryApi = RepositoryApi(application)
    private val mAllTask:LiveData<List<Task>>?

    init {

        mAllTask = mRepositoryApi.retrieveAllTask()
    }

    fun retrievveUserById(id: Int): LiveData<User>?{
        return mRepositoryApi.retrieveUserById(id)
    }

    fun retrieveAllTask():LiveData<List<Task>>?{
        return mAllTask
    }

    fun insertTags(tags:List<Tag>){
        mRepositoryApi.insertTags(tags)
    }
}