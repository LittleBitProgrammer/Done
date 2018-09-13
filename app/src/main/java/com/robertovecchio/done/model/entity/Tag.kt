package com.robertovecchio.done.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "TAG")
data class Tag(@PrimaryKey(autoGenerate = true) var tagId: Int,
               @ColumnInfo(name = "color") var color: String,
               @ColumnInfo(name = "type") var type: String){
    constructor():this(0,"","")
}