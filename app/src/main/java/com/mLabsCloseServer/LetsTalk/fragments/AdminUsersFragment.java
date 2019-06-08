package com.mLabsCloseServer.LetsTalk.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mLabsCloseServer.LetsTalk.R;
import com.mLabsCloseServer.LetsTalk.adapters.UsersNewAdapter;
import com.mLabsCloseServer.LetsTalk.models.UserNew;

import java.util.ArrayList;

public class AdminUsersFragment extends Fragment {

    public  static ArrayList<UserNew> users_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.admin_fragment,
                            container,false);

        RecyclerView rView = v.findViewById(R.id.rview);

        rView.setLayoutManager(new LinearLayoutManager(
                getActivity(),LinearLayoutManager.VERTICAL,
                false));

        UsersNewAdapter aDapter = new UsersNewAdapter(getActivity(), users_list);

        rView.setAdapter(aDapter);

        return v;
    }
}
