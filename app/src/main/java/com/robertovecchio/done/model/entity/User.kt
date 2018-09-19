package com.robertovecchio.done.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.net.Uri

@Entity(tableName = "USER")
data class User(

        @PrimaryKey(autoGenerate = true) var id: Int,
        @ColumnInfo(name = "name")var userName: String,
        @ColumnInfo(name = "image") var image: String?)

{

constructor():this(0,"","")

}