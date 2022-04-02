package com.codecamp.mvvm_retrofit_crud.util

import android.content.Context
import android.widget.Toast

fun Context.toast(message : String){
    Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
}