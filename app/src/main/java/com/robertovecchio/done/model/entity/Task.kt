package com.robertovecchio.done.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "TASK")
data class Task(@PrimaryKey(autoGenerate = true) var taskId: Int,
                @ColumnInfo(name = "tag") var tagId: Int,
                @ColumnInfo(name = "title") var title: String,
                @ColumnInfo(name = "description") var description: String,
                @ColumnInfo(name = "isDone") var isDone: Boolean){
    constructor():this(0,0,"","",false)
}