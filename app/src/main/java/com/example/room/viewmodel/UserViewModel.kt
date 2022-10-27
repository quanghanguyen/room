package com.example.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.room.data.UserDatabase
import com.example.room.repository.UserRepository
import com.example.room.model.User
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class UserViewModel (application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
//    val readAllData = MutableLiveData<ReadAllData>()
    private val repository: UserRepository
    val updateData = MutableLiveData<UpdateData>()

//    sealed class ReadAllData {
//        class ResultOk(val list : List<User>) : ReadAllData()
//        object ResultError : ReadAllData()
//    }

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    sealed class UpdateData {
        class ResultOk(val successMessage : String): UpdateData()
        class ResultError(val errorMessage : String): UpdateData()
    }

    fun addUser(user: User) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            repository.updateUser(user, {
                updateData.value = UpdateData.ResultOk(it)
            }, {
                updateData.value = UpdateData.ResultError(it)
            })
        }
    }
}