package com.codecamp.mvvm_retrofit_crud.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.codecamp.mvvm_retrofit_crud.R
import com.codecamp.mvvm_retrofit_crud.datamodel.User
import com.codecamp.mvvm_retrofit_crud.util.fadeInAnim
import com.codecamp.mvvm_retrofit_crud.view.MainActivity
import com.codecamp.mvvm_retrofit_crud.view.UpdateUserActivity
import com.codecamp.mvvm_retrofit_crud.viewholder.UserViewHolder
import com.codecamp.mvvm_retrofit_crud.viewmodel.UpdateUserViewModel

class UserAdapter(var context : Context) : RecyclerView.Adapter<UserViewHolder>(),UserViewHolder.onClickedItem{
    var userList = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_item,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.onItemClick(position)
        val constraintLayout = arrayListOf<ConstraintLayout>(holder.updateButton,holder.deleteButton)
//        holder.updateButton.setOnClickListener {
//            val intent = Intent(context,UpdateUserActivity::class.java)
//            intent.putExtra("user_id",userList[position].id)
//            context.startActivity(intent)
//            (context as MainActivity).finish()
//        }

        if(constraintLayout[0] == holder.updateButton){
            holder.onClickListner(holder.updateButton,position,holder,::onUpdate)
        }
        if(constraintLayout[1] == holder.deleteButton){
            holder.onClickListner(holder.deleteButton,position,holder,::onDelete)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onDelete(position : Int,holder: UserViewHolder) {
        val intent = Intent(context,UpdateUserActivity::class.java)
        intent.putExtra("user_id",userList[position].id)
        intent.putExtra("button_text","Delete User")
        context.startActivity(intent)
        (context as MainActivity).finish()

    }

    override fun onUpdate(position : Int,holder: UserViewHolder) {
        val intent = Intent(context,UpdateUserActivity::class.java)
        intent.putExtra("user_id",userList[position].id)
        intent.putExtra("button_text","Update User")
        context.startActivity(intent)
        (context as MainActivity).finish()

    }


}
