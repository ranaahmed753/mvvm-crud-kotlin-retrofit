package com.codecamp.mvvm_retrofit_crud.model

import androidx.lifecycle.MutableLiveData
import com.codecamp.mvvm_retrofit_crud.datamodel.UserResponse
import com.codecamp.mvvm_retrofit_crud.internet.RetrofitInstance
import com.codecamp.mvvm_retrofit_crud.internet.RetrofitService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class DeleteUserRepository {
    val deleteUserLiveData : MutableLiveData<UserResponse>
    init {
        deleteUserLiveData = MutableLiveData()
    }
    fun deleteUser(user_id : String){
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        val call = retrofitInstance.deleteUser(user_id)
        call.enqueue(object : retrofit2.Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){
                    deleteUserLiveData.postValue(response.body())
                }else{
                    deleteUserLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                deleteUserLiveData.postValue(null)
            }

        })
    }
    fun getDeleteUserMutableLiveData() : MutableLiveData<UserResponse>{
        return deleteUserLiveData
    }
}