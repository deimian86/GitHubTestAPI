package com.damgonzalez.githubapitest.core.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.damgonzalez.githubapitest.core.model.Node
import com.damgonzalez.githubapitest.core.model.User

@Dao
interface UserDataDao {

    @Insert(onConflict = REPLACE)
    fun insert(user: User)

    @Insert(onConflict = REPLACE)
    fun insertAll(objects: ArrayList<User>)

    @Query("DELETE from userData")
    fun deleteAll()

    @Query("SELECT * FROM userData WHERE id == :id")
    fun getUserById(id: String): List<User>

    @Query("SELECT * from userData")
    fun getAll(): List<User>

}