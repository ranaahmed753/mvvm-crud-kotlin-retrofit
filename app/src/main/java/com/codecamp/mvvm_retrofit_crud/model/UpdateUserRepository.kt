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
import retrofit2.create

class UpdateUserRepository {
    var updateUserMutableLiveData : MutableLiveData<UserResponse>
    init {
        updateUserMutableLiveData = MutableLiveData()
    }
    fun updateUser(user_id : String,params : User){
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        val call = retrofitInstance.updateUser(user_id,params)
        call.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){
                    updateUserMutableLiveData.postValue(response.body())
                }else{
                    updateUserMutableLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                updateUserMutableLiveData.postValue(null)
            }

        })
    }
    fun getUpdateMutableliveData() : MutableLiveData<UserResponse>{
        return updateUserMutableLiveData
    }
}