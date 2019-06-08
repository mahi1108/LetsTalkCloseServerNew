package com.mLabsCloseServer.LetsTalk.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.mLabsCloseServer.LetsTalk.R;
import com.mLabsCloseServer.LetsTalk.models.UserNew;
import com.mLabsCloseServer.LetsTalk.utils.Helper;
import com.mLabsCloseServer.LetsTalk.viewHolders.UserHolder;

import java.util.ArrayList;

public class UsersNewAdapter extends
        RecyclerView.Adapter<UserHolder> {

    ArrayList<UserNew> list;
    Activity activity;

    public UsersNewAdapter(Activity activity, ArrayList<UserNew> users_list) {
        this.list = users_list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(activity);

        View v = inflater.inflate(R.layout.rc_org_request_item,
                                parent,false);

        return new UserHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, final int position) {

        holder.uName.setText(list.get(position).getContactName());
        holder.uDept.setText(list.get(position).getDept());

        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase dBase = FirebaseDatabase.getInstance();
                DatabaseReference dBRef = dBase.getReference(Helper.REF_USERS+"/"+list.get(position).getId()+"/authentication");
                dBRef.setValue(true);

            }
        });

        holder.btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase dBase = FirebaseDatabase.getInstance();
                DatabaseReference dBRef = dBase.getReference(Helper.REF_USERS+"/"+list.get(position).getId());
                dBRef.removeValue();

            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}
