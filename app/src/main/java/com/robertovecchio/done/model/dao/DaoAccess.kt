package com.robertovecchio.done.model.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.robertovecchio.done.model.entity.Tag
import com.robertovecchio.done.model.entity.Task
import com.robertovecchio.done.model.entity.User

@Dao
interface DaoAccess {

    @Insert
    fun insertUser(user:User)

    @Insert
    fun insertTask(task: Task)

    @Insert
    fun insertTag(tag:Tag)

    @Insert
    fun insertTags(tags: List<Tag>)

    @Delete
    fun deleteTask(task: Task)

    @Query("SELECT * FROM USER WHERE id = :id")
    fun retrieveUserById(id: Int): LiveData<User>

    @Query("SELECT * FROM TASK WHERE taskId = :taskId")
    fun retrieveTaskById(taskId:Int): LiveData<Task>

    @Query("SELECT * FROM TASK")
    fun retrieveAllTask(): LiveData<List<Task>>

    @Query("SELECT * FROM TAG WHERE tagId = :tagId")
    fun retrieveTagById(tagId: Int): LiveData<Tag>

    @Query("SELECT * FROM TAG")
    fun retrieveAllTag(): LiveData<List<Tag>>
}