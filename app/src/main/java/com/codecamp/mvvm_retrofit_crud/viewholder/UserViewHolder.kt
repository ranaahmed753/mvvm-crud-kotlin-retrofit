package com.codecamp.mvvm_retrofit_crud.viewholder

import android.content.Context
import android.os.Parcelable
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.codecamp.mvvm_retrofit_crud.R
import com.codecamp.mvvm_retrofit_crud.datamodel.User

class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    var userName : TextView
    var userEmail : TextView
    var userStatus : TextView
    var constraintLayout : ConstraintLayout
    var updateButton : ConstraintLayout
    var deleteButton : ConstraintLayout

    init {
        userName = itemView.findViewById(R.id.name)
        userEmail = itemView.findViewById(R.id.email)
        userStatus = itemView.findViewById(R.id.status)
        constraintLayout = itemView.findViewById(R.id.constraintLayout)
        updateButton = itemView.findViewById(R.id.updateButton)
        deleteButton = itemView.findViewById(R.id.deleteButton )
    }
    fun bind(data : User){
        userName.text = data.name
        userEmail.text = data.email
        userStatus.text = data.status
    }
    fun onClickListner(widget : View, position : Int,holder : UserViewHolder,onDoSomething : (position : Int,holder : UserViewHolder) -> Unit){
        widget.setOnClickListener {
            onDoSomething(position,holder)
        }
    }
    fun onItemClick(position : Int){
        constraintLayout.setOnClickListener {
            //println(tex)
        }

    }

    interface onClickedItem{
        fun onDelete(position : Int,holder: UserViewHolder)
        fun onUpdate(position : Int,holder: UserViewHolder)
    }

}