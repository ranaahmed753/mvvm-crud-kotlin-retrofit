package com.codecamp.mvvm_retrofit_crud.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecamp.mvvm_retrofit_crud.datamodel.UserResponse
import com.codecamp.mvvm_retrofit_crud.model.DeleteUserRepository

class DeleteUserViewModel : ViewModel() {
    val deleteUserRepository : DeleteUserRepository
    var deleteUserMutableLiveData : MutableLiveData<UserResponse>
    init {
        deleteUserRepository = DeleteUserRepository()
        deleteUserMutableLiveData = deleteUserRepository.getDeleteUserMutableLiveData()
    }
    fun deleteUser(user_id : String){
        deleteUserRepository.deleteUser(user_id)
    }
    fun getDeleteLivaData() : MutableLiveData<UserResponse>{
        return deleteUserMutableLiveData
    }
}