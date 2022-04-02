package com.codecamp.mvvm_retrofit_crud.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecamp.mvvm_retrofit_crud.datamodel.User
import com.codecamp.mvvm_retrofit_crud.datamodel.UserList
import com.codecamp.mvvm_retrofit_crud.datamodel.UserResponse
import com.codecamp.mvvm_retrofit_crud.model.UpdateUserRepository

class UpdateUserViewModel : ViewModel() {
    val updateUserRepository : UpdateUserRepository
    val updateUserLiveData : MutableLiveData<UserResponse>
    init {
        updateUserRepository = UpdateUserRepository()
        updateUserLiveData = updateUserRepository.getUpdateMutableliveData()
    }
    fun updateUser(user_id : String,params : User){
        updateUserRepository.updateUser(user_id,params)
    }
    fun getUpdateLiveData() : MutableLiveData<UserResponse>{
        return updateUserLiveData
    }
}