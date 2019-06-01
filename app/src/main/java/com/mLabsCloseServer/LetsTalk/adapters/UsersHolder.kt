package com.mLabsCloseServer.LetsTalk.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.mLabsCloseServer.LetsTalk.R
import de.hdodenhof.circleimageview.CircleImageView

class UsersHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var letstalk_logo:CircleImageView? = null
    var rec_username:TextView? = null
    var txtDept:TextView? = null

    init {
        letstalk_logo = itemView?.findViewById(R.id.letstalk_logo) as CircleImageView
        rec_username =  itemView?.findViewById(R.id.rec_username) as TextView
        txtDept = itemView?.findViewById(R.id.txtDept) as TextView
    }
}