package com.mLabsCloseServer.LetsTalk.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mLabsCloseServer.LetsTalk.R
import com.mLabsCloseServer.LetsTalk.activities.MainActivity
import com.mLabsCloseServer.LetsTalk.models.UserInfo
import java.util.ArrayList

class UsersAdapter(var mainActivity: MainActivity, var users_data: ArrayList<UserInfo>) : RecyclerView.Adapter<UsersHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {

        var inflater = LayoutInflater.from(mainActivity)
        var v = inflater.inflate(R.layout.rc_org_request_item_user,parent,false)

        return  UsersHolder(v)
    }

    override fun getItemCount(): Int  = users_data.size

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        holder.rec_username?.text = users_data?.get(position)?.userNew?.person_name
        holder.txtDept?.text =  users_data?.get(position)?.userNew?.dept
    }
}