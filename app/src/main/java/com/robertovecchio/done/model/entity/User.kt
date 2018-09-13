package com.robertovecchio.done.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "USER")
data class User(

        @PrimaryKey(autoGenerate = false) var userName: String,
        @ColumnInfo(name = "image") var image: String?)

{

constructor():this("","")

}