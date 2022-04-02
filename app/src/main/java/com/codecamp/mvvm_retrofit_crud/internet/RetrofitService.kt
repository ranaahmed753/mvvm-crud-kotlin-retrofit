package com.codecamp.mvvm_retrofit_crud.internet

import com.codecamp.mvvm_retrofit_crud.datamodel.User
import com.codecamp.mvvm_retrofit_crud.datamodel.UserList
import com.codecamp.mvvm_retrofit_crud.datamodel.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @GET("users")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getUserList() : Call<UserList>

    @POST("users")
    @Headers("Accept:application/json","Content-Type:application/json","Authorization: Bearer e459be3a7cddb753b2e52f333162c2866911a28fef7402f8b532ec5de0b30a04")
    fun createUser(@Body params : User) : Call<UserResponse>

    @GET("users")
    @Headers("Accept:application/json\",\"Content-Type:application/json")
    fun searchUser(@Query("name") searchText : String) : Call<UserList>

    @GET("users/{user_id}")
    @Headers("Accept:application/json\",\"Content-Type:application/json")
    fun getUserById(@Path("user_id") user_id : String) : Call<UserList>

    @PATCH("users/{user_id}")
    @Headers("Accept:application/json\",\"Content-Type:application/json\",\"Authorization: Bearer e459be3a7cddb753b2e52f333162c2866911a28fef7402f8b532ec5de0b30a04")
    fun updateUser(@Path("user_id") user_id : String,@Body params : User) : Call<UserResponse>

    @DELETE("users/{user_id}")
    @Headers("Accept:application/json\",\"Content-Type:application/json\",\"Authorization: Bearer e459be3a7cddb753b2e52f333162c2866911a28fef7402f8b532ec5de0b30a04")
    fun deleteUser(@Path("user_id") user_id : String) : Call<UserResponse>
}
