package com.codecamp.mvvm_retrofit_crud.util

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils

fun Context.fadeInAnim(widget : View){
    widget.startAnimation(AnimationUtils.loadAnimation(applicationContext,android.R.anim.fade_in))
}