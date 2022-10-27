package com.example.room.repository

import androidx.lifecycle.LiveData
import com.example.room.data.UserDao
import com.example.room.model.User

class UserRepository (private val userDao: UserDao) {

    val readAllData : LiveData<List<User>> = userDao.realAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser (
        user: User,
        onSuccess : (String) -> Unit,
        onFail : (String) -> Unit
        ) {
        userDao.updateUser(user)
        onSuccess("Success")
        onFail("Error")
    }
}