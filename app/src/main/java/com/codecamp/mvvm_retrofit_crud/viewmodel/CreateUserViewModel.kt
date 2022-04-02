package com.codecamp.mvvm_retrofit_crud.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecamp.mvvm_retrofit_crud.datamodel.User
import com.codecamp.mvvm_retrofit_crud.datamodel.UserResponse
import com.codecamp.mvvm_retrofit_crud.model.CreateUserRepository

class CreateUserViewModel : ViewModel() {
    var createUserRepository : CreateUserRepository
    var createUserLiveData : MutableLiveData<UserResponse>

    init {
        createUserRepository = CreateUserRepository()
        createUserLiveData = createUserRepository.getCreatedUserMutableLiveData()
    }
    fun createUser(user : User){
        createUserRepository.createUser(user)
    }
    fun getCreatedUserLiveData() : MutableLiveData<UserResponse>{
        return createUserLiveData
    }

}