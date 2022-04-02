package com.codecamp.mvvm_retrofit_crud.model

import androidx.lifecycle.MutableLiveData
import com.codecamp.mvvm_retrofit_crud.datamodel.User
import com.codecamp.mvvm_retrofit_crud.datamodel.UserList
import com.codecamp.mvvm_retrofit_crud.datamodel.UserResponse
import com.codecamp.mvvm_retrofit_crud.internet.RetrofitInstance
import com.codecamp.mvvm_retrofit_crud.internet.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateUserRepository {
    var createUserMutableLiveData : MutableLiveData<UserResponse>
    init {
        createUserMutableLiveData = MutableLiveData()
    }
    fun getCreatedUserMutableLiveData() : MutableLiveData<UserResponse>{
        return createUserMutableLiveData
    }
    fun createUser(user : User){
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        val call = retrofitInstance.createUser(user)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){
                    createUserMutableLiveData.postValue(response.body())
                }else{
                    createUserMutableLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                createUserMutableLiveData.postValue(null)
            }

        })
    }
}