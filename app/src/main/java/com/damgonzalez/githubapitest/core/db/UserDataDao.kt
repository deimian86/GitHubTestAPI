package com.damgonzalez.githubapitest.core.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.damgonzalez.githubapitest.core.model.User

@Dao
interface UserDataDao {

    @Query("SELECT * from userData")
    fun getAll(): List<User>

    @Insert(onConflict = REPLACE)
    fun insert(user: User)

    @Query("DELETE from userData")
    fun deleteAll()
}