package com.backend.csi5175.DAO

import androidx.room.*
import com.backend.csi5175.model.Order
import com.backend.csi5175.model.Product
import com.backend.csi5175.model.User

@Dao
interface UserDao
{
    @Query("SELECT * FROM user WHERE uid = :uid LIMIT 1")
    fun findUserByUid(uid: Int): User
    @Insert
    fun insert(vararg users: User)

    @Update
    fun updateUserInfo(user: User)

    @Delete
    fun delete(user: User)
}