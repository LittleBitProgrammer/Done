package com.robertovecchio.done.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TAG")
data class Tag(@PrimaryKey(autoGenerate = true) var tagId: Int,
               @ColumnInfo(name = "color") var color: String,
               @ColumnInfo(name = "type") var type: String){
    constructor():this(0,"","")
}