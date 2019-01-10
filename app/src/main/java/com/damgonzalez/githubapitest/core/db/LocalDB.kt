package com.damgonzalez.githubapitest.core.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.content.Context
import com.damgonzalez.githubapitest.core.model.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class LocalDB : RoomDatabase() {

    abstract fun userDataDao(): UserDataDao

    companion object {
        private var INSTANCE: LocalDB? = null

        fun getInstance(context: Context): LocalDB? {
            if (INSTANCE == null) {
                synchronized(LocalDB::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        LocalDB::class.java, "local.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}