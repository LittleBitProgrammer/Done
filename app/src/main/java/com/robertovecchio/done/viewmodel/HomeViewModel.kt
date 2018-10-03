package com.robertovecchio.done.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.robertovecchio.done.model.database.DoneDatabase
import com.robertovecchio.done.model.entity.Tag
import com.robertovecchio.done.model.entity.Task
import com.robertovecchio.done.model.entity.User
import com.robertovecchio.done.model.repository.RepositoryApi
import javax.inject.Inject

class HomeViewModel @Inject constructor(application: Application): ViewModel() {

    private val mRepositoryApi: RepositoryApi = RepositoryApi(application)
    private val mAllTask: LiveData<List<Task>>?

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

    override fun onCleared() {
        super.onCleared()
        DoneDatabase.destroyInstance()
    }
}