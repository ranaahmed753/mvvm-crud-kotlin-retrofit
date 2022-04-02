package com.codecamp.mvvm_retrofit_crud.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codecamp.mvvm_retrofit_crud.R
import com.codecamp.mvvm_retrofit_crud.datamodel.User
import com.codecamp.mvvm_retrofit_crud.datamodel.UserList
import com.codecamp.mvvm_retrofit_crud.datamodel.UserResponse
import com.codecamp.mvvm_retrofit_crud.util.fadeInAnim
import com.codecamp.mvvm_retrofit_crud.util.navigate
import com.codecamp.mvvm_retrofit_crud.util.toast
import com.codecamp.mvvm_retrofit_crud.viewmodel.DeleteUserViewModel
import com.codecamp.mvvm_retrofit_crud.viewmodel.UpdateUserViewModel

class UpdateUserActivity : AppCompatActivity() {
    private lateinit var name : EditText
    private lateinit var email : EditText
    private lateinit var updateUserButton : ConstraintLayout
    private lateinit var backButton : ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var updateUserViewModel: UpdateUserViewModel
    private lateinit var deleteUserViewModel: DeleteUserViewModel
    private lateinit var updateUserText : TextView
    private lateinit var nameLayout : ConstraintLayout
    private lateinit var emailLayout : ConstraintLayout
    //private var user_id : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)

        val user_id = intent.getStringExtra("user_id")
        val button_text = intent.getStringExtra("button_text")

        nameLayout = findViewById(R.id.nameLayout)
        emailLayout = findViewById(R.id.emailLayout
        )
        backButton = findViewById(R.id.backButton)
        name = findViewById(R.id.nameEditText)
        email = findViewById(R.id.emailEditText)
        updateUserButton = findViewById(R.id.updateUserButton)
        progressBar = findViewById(R.id.progressbar)
        updateUserText = findViewById(R.id.updateUserText)
        updateUserText.text = button_text
        if(button_text.equals("Update User")){
            nameLayout.visibility = View.VISIBLE
            emailLayout.visibility = View.VISIBLE
            updateUserButton.setOnClickListener {
                fadeInAnim(updateUserButton)
                if(TextUtils.isEmpty(name.text.toString()) || TextUtils.isEmpty(email.text.toString())){
                    toast("name or password is empty")
                }else{
                    updateUserText.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                    updateUserViewModel = ViewModelProvider(this).get(UpdateUserViewModel::class.java)
                    updateUserViewModel.updateUser(user_id.toString(),User("",name.text.toString(),email.text.toString(),"",""))
                    updateUserViewModel.getUpdateLiveData().observe(this@UpdateUserActivity, Observer<UserResponse> { user ->
                        if(user != null){
                            progressBar.visibility = View.GONE
                            updateUserText.visibility = View.VISIBLE
                            name.text.clear()
                            email.text.clear()
                            toast("user updated successfully")
                        }else{
                            progressBar.visibility = View.GONE
                            updateUserText.visibility = View.VISIBLE
                            toast("user updated failed")
                            println("User Id :- "+user_id)
                        }
                    })

                }
            }
        }

        if(button_text.equals("Delete User")){
            nameLayout.visibility = View.GONE
            emailLayout.visibility = View.GONE
            updateUserButton.setOnClickListener {
                fadeInAnim(updateUserButton)
                updateUserText.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                deleteUserViewModel = ViewModelProvider(this).get(DeleteUserViewModel::class.java)
                deleteUserViewModel.deleteUser(user_id.toString())
                    deleteUserViewModel.getDeleteLivaData().observe(this@UpdateUserActivity, Observer<UserResponse> { user ->
                        if(user != null){
                            progressBar.visibility = View.GONE
                            updateUserText.visibility = View.VISIBLE
                            toast(" ID $user_id deleted successfully")
                        }else{
                            progressBar.visibility = View.GONE
                            updateUserText.visibility = View.VISIBLE
                            toast("ID $user_id deleted failed")
                            println("User Id :- "+user_id)
                        }
                    })
            }
        }


        backButton.setOnClickListener {
            fadeInAnim(backButton)
            navigate(this@UpdateUserActivity,MainActivity())
        }

    }
}