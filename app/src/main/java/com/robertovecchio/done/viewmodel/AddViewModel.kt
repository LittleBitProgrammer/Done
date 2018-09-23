package com.robertovecchio.done.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.robertovecchio.done.model.database.DoneDatabase
import com.robertovecchio.done.model.repository.RepositoryApi
import com.robertovecchio.done.model.entity.Tag

class AddViewModel constructor(application: Application): AndroidViewModel(application) {

    private val mRepositoryApi: RepositoryApi = RepositoryApi(application)
    private val allTags: LiveData<List<Tag>>?

    init {
        allTags = mRepositoryApi.retrieveAllTag()
    }

    fun getAllTags(): LiveData<List<Tag>>?{
        return allTags
    }

    override fun onCleared() {
        super.onCleared()
        DoneDatabase.destroyInstance()
    }
}