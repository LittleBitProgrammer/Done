package com.robertovecchio.done.model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.robertovecchio.done.model.dao.DaoAccess
import com.robertovecchio.done.model.database.DoneDatabase
import com.robertovecchio.done.model.entity.Tag
import com.robertovecchio.done.model.entity.Task
import com.robertovecchio.done.model.entity.User
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class RepositoryApi internal constructor(application: Application){

    private val doneDao: DaoAccess?
    private val mAllTask: LiveData<List<Task>>?
    private val mAllTag: LiveData<List<Tag>>?

    init {
        val db = DoneDatabase.getInstance(application)
        doneDao = db?.daoAccess()
        mAllTask = doneDao?.retrieveAllTask()
        mAllTag = doneDao?.retrieveAllTag()
    }

    //INSERT
    fun insertUser(user: User){
        Completable.fromAction { doneDao?.insertUser(user) }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun insertTask(task: Task){
        Completable.fromAction { doneDao?.insertTask(task) }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun insertTag(tag:Tag){
        Completable.fromAction { doneDao?.insertTag(tag) }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun insertTags(tags: List<Tag>){
        Completable.fromAction { doneDao?.insertTags(tags) }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    //DELETE
    fun deleteTask(task:Task){
        Completable.fromAction { doneDao?.deleteTask(task) }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    //QUERY
    fun retrieveUserById(id: Int):LiveData<User>?{
        return doneDao?.retrieveUserById(id)
    }

    fun retrieveTaskById(taskId: Int):LiveData<Task>?{
        return doneDao?.retrieveTaskById(taskId)
    }

    fun retrieveAllTask():LiveData<List<Task>>?{
        return mAllTask
    }

    fun retrieveTagById(tagId:Int):LiveData<Tag>?{
        return doneDao?.retrieveTagById(tagId)
    }

    fun retrieveAllTag(): LiveData<List<Tag>>?{
        return  doneDao?.retrieveAllTag()
    }
}