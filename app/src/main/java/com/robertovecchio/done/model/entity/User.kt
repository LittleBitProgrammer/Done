package com.robertovecchio.done.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER")
data class User(

        @PrimaryKey(autoGenerate = true) var id: Int,
        @ColumnInfo(name = "name")var userName: String,
        @ColumnInfo(name = "image") var image: String?)

{

constructor():this(0,"","")

}