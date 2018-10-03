package com.robertovecchio.done.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TASK")
data class Task(@PrimaryKey(autoGenerate = true) var taskId: Int,
                @ColumnInfo(name = "tag") var tagId: Int,
                @ColumnInfo(name = "title") var title: String,
                @ColumnInfo(name = "description") var description: String,
                @ColumnInfo(name = "isDone") var isDone: Boolean){
    constructor():this(0,0,"","",false)
}