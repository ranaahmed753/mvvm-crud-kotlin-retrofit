package com.codecamp.mvvm_retrofit_crud.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codecamp.mvvm_retrofit_crud.datamodel.UserList
import com.codecamp.mvvm_retrofit_crud.internet.RetrofitInstance
import com.codecamp.mvvm_retrofit_crud.internet.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class UserRepository{
    var userMutableLiveData : MutableLiveData<UserList>

    init {
        userMutableLiveData = MutableLiveData()
    }

    fun getUserList(){
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        val call = retrofitInstance.getUserList()
        call.enqueue(object : Callback<UserList>{
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if(response.isSuccessful){
                    userMutableLiveData.postValue(response.body())
                }else{
                    userMutableLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                userMutableLiveData.postValue(null)
            }

        })
    }

    fun searchUser(searchText : String){
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        val call = retrofitInstance.searchUser(searchText)
        call.enqueue(object : Callback<UserList>{
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if(response.isSuccessful){
                    userMutableLiveData.postValue(response.body())
                }else{
                    userMutableLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                userMutableLiveData.postValue(null)
            }

        })
    }

    fun getRecyclerListData() : MutableLiveData<UserList>{
        return userMutableLiveData
    }
}