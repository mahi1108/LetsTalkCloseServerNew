package com.mLabsCloseServer.LetsTalk.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mLabsCloseServer.LetsTalk.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserHolder extends RecyclerView.ViewHolder {

    public CircleImageView cView;
    public TextView uName;
    public TextView uDept;
    public  Button btnAccept;
    public Button btnReject;

    public UserHolder(View itemView) {
        super(itemView);
        cView = itemView.findViewById(R.id.circle_view);
        uName = itemView.findViewById(R.id.rec_username);
        uDept = itemView.findViewById(R.id.txtDept);
        btnAccept = itemView.findViewById(R.id.btnAcceptReq);
        btnReject = itemView.findViewById(R.id.btnRejectReq);
     }
}
