package com.mLabsCloseServer.LetsTalk.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mLabsCloseServer.LetsTalk.R;
import com.mLabsCloseServer.LetsTalk.adapters.MenuUsersRecyclerAdapter;
import com.mLabsCloseServer.LetsTalk.interfaces.UserGroupSelectionDismissListener;
import com.mLabsCloseServer.LetsTalk.models.User;

import java.util.ArrayList;
import java.util.Objects;

public class AddFriendToCallFragment extends com.mLabsCloseServer.LetsTalk.fragments.BaseFullDialogFragment {
    private TextView heading;
    private EditText query;
    private RecyclerView usersRecycler;
    private ArrayList<User> myUsers;

    public AddFriendToCallFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_select, container);
        heading = view.findViewById(R.id.heading);
        query = view.findViewById(R.id.searchQuery);
        usersRecycler = view.findViewById(R.id.callUsersRecycler);
        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        usersRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            usersRecycler.setAdapter(new MenuUsersRecyclerAdapter(Objects.requireNonNull(getActivity()), myUsers));
        }
        query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (usersRecycler.getAdapter() instanceof MenuUsersRecyclerAdapter) {
                    ((MenuUsersRecyclerAdapter) usersRecycler.getAdapter()).getFilter().filter(editable.toString());
                }
            }
        });
    }

    public void refreshUsers(int pos) {
        if (pos == -1) {
            query.setText("");
            usersRecycler.getAdapter().notifyDataSetChanged();
        } else {
            usersRecycler.getAdapter().notifyItemChanged(pos);
        }
    }

    public static AddFriendToCallFragment newInstance(Context context, ArrayList<User> myUsers) {
        AddFriendToCallFragment dialogFragment = new AddFriendToCallFragment();
        dialogFragment.myUsers = myUsers;
        if (context instanceof UserGroupSelectionDismissListener) {
            dialogFragment.dismissListener = (UserGroupSelectionDismissListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement UserGroupSelectionDismissListener");
        }
        return dialogFragment;
    }
}
