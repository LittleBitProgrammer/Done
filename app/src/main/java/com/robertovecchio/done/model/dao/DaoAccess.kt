package com.robertovecchio.done.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
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