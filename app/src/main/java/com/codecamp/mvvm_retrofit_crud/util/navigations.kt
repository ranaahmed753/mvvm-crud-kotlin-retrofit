package com.codecamp.mvvm_retrofit_crud.util

import android.app.Activity
import android.content.Context
import android.content.Intent

fun Context.navigate(currentContext : Context,targetContext : Context){
    startActivity(Intent(currentContext,targetContext::class.java))

}