package com.robertovecchio.done.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.robertovecchio.done.model.entity.User
import com.robertovecchio.done.model.repository.RepositoryApi

class SubscribeViewModel constructor(application: Application): AndroidViewModel(application) {
    private val mRepositoryApi: RepositoryApi = RepositoryApi(application)

    fun insertUser(user: User){
        mRepositoryApi.insertUser(user)
    }
}