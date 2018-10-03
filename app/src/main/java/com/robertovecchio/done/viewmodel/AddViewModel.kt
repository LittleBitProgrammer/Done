package com.robertovecchio.done.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.robertovecchio.done.model.database.DoneDatabase
import com.robertovecchio.done.model.repository.RepositoryApi
import com.robertovecchio.done.model.entity.Tag
import javax.inject.Inject

class AddViewModel @Inject constructor(application: Application): ViewModel() {

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