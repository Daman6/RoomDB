package com.example.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.roomdb.data.User
import com.example.roomdb.data.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData : LiveData<List<User>> = userDao.getUser()

    suspend fun addUser(user: User){
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser(){
        userDao.deleteAllUser()
    }
}