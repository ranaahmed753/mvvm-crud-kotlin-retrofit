package com.codecamp.mvvm_retrofit_crud.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecamp.mvvm_retrofit_crud.datamodel.UserList
import com.codecamp.mvvm_retrofit_crud.model.UserRepository

class UserViewModel : ViewModel() {
    var userRepository : UserRepository
    var userMutableLiveData : MutableLiveData<UserList>
    init {
        userRepository = UserRepository()
        userMutableLiveData = userRepository.getRecyclerListData()
    }
    fun getUserList(){
        userRepository.getUserList()
    }
    fun searchUser(searchText : String){
        userRepository.searchUser(searchText)
    }
    fun getUserLiveData() : MutableLiveData<UserList>{
        return userMutableLiveData
    }
}