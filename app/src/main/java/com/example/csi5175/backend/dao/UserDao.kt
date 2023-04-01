package com.example.csi5175.backend.dao

import androidx.room.*
import com.example.csi5175.backend.model.Product
import com.example.csi5175.backend.model.User

@Dao
interface UserDao
{
    @Query("SELECT * FROM user WHERE uid = :uid LIMIT 1")
    fun findUserByUid(uid: Int): User

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE email =:email AND password =:password LIMIT 1")
    fun getUserByEmailAndPassword(email: String, password: String): User

    @Insert
    fun insert(vararg users: User)

    @Update
    fun updateUserInfo(user: User)

    @Query("DELETE FROM user WHERE uid = :uid")
    fun delete(uid: Int)

    @Query("UPDATE user SET cart = :emptyList WHERE uid =:uid")
    fun cleanCart(uid: Int, emptyList: List<Product> = ArrayList<Product>())
}