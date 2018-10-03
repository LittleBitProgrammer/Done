package com.robertovecchio.done.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.robertovecchio.done.model.dao.DaoAccess
import com.robertovecchio.done.model.entity.Tag
import com.robertovecchio.done.model.entity.Task
import com.robertovecchio.done.model.entity.User

@Database(entities = [User::class, Task::class, Tag::class], version = 2)
abstract class DoneDatabase: RoomDatabase() {

    abstract fun daoAccess(): DaoAccess

    companion object {

        private var INSTANCE: DoneDatabase? = null

        fun getInstance(context: Context): DoneDatabase? {
            if (INSTANCE == null){
                synchronized(DoneDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DoneDatabase::class.java,"done.db").build()
                }
            }

            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }
}