package com.codecamp.mvvm_retrofit_crud.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codecamp.mvvm_retrofit_crud.R
import com.codecamp.mvvm_retrofit_crud.datamodel.User
import com.codecamp.mvvm_retrofit_crud.datamodel.UserResponse
import com.codecamp.mvvm_retrofit_crud.util.fadeInAnim
import com.codecamp.mvvm_retrofit_crud.util.navigate
import com.codecamp.mvvm_retrofit_crud.util.toast
import com.codecamp.mvvm_retrofit_crud.viewmodel.CreateUserViewModel

class CreateUserActivity : AppCompatActivity() {
    private lateinit var email : EditText
    private lateinit var name : EditText
    private lateinit var backButton : ImageView
    private lateinit var createUserButton : ConstraintLayout
    private lateinit var createUserViewModel : CreateUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        createUserButton = findViewById(R.id.createUserButton)
        email = findViewById(R.id.createUserEmailEditText)
        name = findViewById(R.id.createUserNameEditText)
        backButton = findViewById(R.id.backButton)

        initViewModel()
        createUserObservable()
        backButton.setOnClickListener {
            fadeInAnim(backButton)
            navigate(this@CreateUserActivity,MainActivity())
        }

        createUserButton.setOnClickListener {
            if(TextUtils.isEmpty(email.text.toString()) || TextUtils.isEmpty(name.text.toString())){
                toast("empty name or email!!")
            }else{
                createUser()
            }

        }
    }
    private fun createUser(){
        val user = User("",name.text.toString(),email.text.toString(),"active","male")
        createUserViewModel.createUser(user)
    }
    private fun initViewModel(){
        createUserViewModel = ViewModelProvider(this).get(CreateUserViewModel::class.java)
    }
    private fun createUserObservable(){
        createUserViewModel.getCreatedUserLiveData().observe(this, Observer<UserResponse> { user ->
            if(user == null){
                toast("Failed To create User")
            }else{
                toast("${user.data} created Successfully")
                navigate(this@CreateUserActivity,MainActivity())
            }
        })
    }
}