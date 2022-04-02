package com.codecamp.mvvm_retrofit_crud.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codecamp.mvvm_retrofit_crud.R
import com.codecamp.mvvm_retrofit_crud.adapter.UserAdapter
import com.codecamp.mvvm_retrofit_crud.datamodel.UserList
import com.codecamp.mvvm_retrofit_crud.util.fadeInAnim
import com.codecamp.mvvm_retrofit_crud.util.navigate
import com.codecamp.mvvm_retrofit_crud.util.toast
import com.codecamp.mvvm_retrofit_crud.viewholder.UserViewHolder
import com.codecamp.mvvm_retrofit_crud.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(){
    private lateinit var mRecyclerView : RecyclerView
    private lateinit var mUserAdapter : UserAdapter
    private lateinit var userViewModel : UserViewModel
    private lateinit var searchText : EditText
    private lateinit var searchButton : ConstraintLayout
    private lateinit var createButton : ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createButton = findViewById(R.id.createButton)
        initRecyclerView()
        initViewModel()
        searchUser()
        userViewModel.getUserList()
        createButton.setOnClickListener {
            fadeInAnim(createButton)
            navigate(this,CreateUserActivity())
        }

    }

    private fun initRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview)
        mUserAdapter = UserAdapter(this@MainActivity)
        mRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity,DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            adapter = mUserAdapter
            //initViewModel()

        }
    }
    private fun initViewModel(){
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getUserLiveData().observe(this@MainActivity, Observer<UserList> { userList ->
            if(userList == null){
                toast("nothing found and ${userList}")
            }else{
                mUserAdapter.userList = userList.data?.toMutableList()!!
                mUserAdapter.notifyDataSetChanged()
            }
        })

    }
    private fun searchUser(){
        searchText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            fadeInAnim(searchButton)
            if(!TextUtils.isEmpty(searchText.text.toString())){
                userViewModel.searchUser(searchText.text.toString())
            }else{
                userViewModel.getUserList()
            }
        }
        searchText.addTextChangedListener { editableText ->
            if(editableText?.length != 0){
                userViewModel.searchUser(editableText.toString())
            }else{
                userViewModel.getUserList()
            }
        }
    }


}