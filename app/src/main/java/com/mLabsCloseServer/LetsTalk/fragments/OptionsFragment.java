package com.mLabsCloseServer.LetsTalk.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mLabsCloseServer.LetsTalk.R;
import com.mLabsCloseServer.LetsTalk.activities.settings;
import com.mLabsCloseServer.LetsTalk.models.User2;
import com.mLabsCloseServer.LetsTalk.utils.SharedPreferenceHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.mLabsCloseServer.LetsTalk.models.User;
import com.mLabsCloseServer.LetsTalk.services.SinchService;
import com.mLabsCloseServer.LetsTalk.utils.ConfirmationDialogFragment;
import com.mLabsCloseServer.LetsTalk.utils.Helper;

/**
 * Created by a_man on 01-01-2018.
 */

public class OptionsFragment extends com.mLabsCloseServer.LetsTalk.fragments.BaseFullDialogFragment {
    private static String CONFIRM_TAG = "confirmtag";
    private static String PRIVACY_TAG = "privacytag";
    private static String PROFILE_EDIT_TAG = "profileedittag";
    private ImageView userImage;
    private TextView userName, userStatus;
    private Helper helper;
    private SinchService.SinchServiceInterface sinchServiceInterface;
    private User userMe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_options, container);
        userImage = view.findViewById(R.id.userImage);
        userName = view.findViewById(R.id.userName);
        userStatus = view.findViewById(R.id.userStatus);
        final SharedPreferenceHelper prefHelper = SharedPreferenceHelper.getInstance(getContext());


        setUserDetails();
        view.findViewById(R.id.Security).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View vewInflater = getLayoutInflater().inflate(R.layout.dialog_edit_phone,null);
                final EditText input = (EditText)vewInflater.findViewById(R.id.edit_phone);
                //input.setText("someone@email.com");
                /*Hiển thị dialog với dEitText cho phép người dùng nhập username mới*/
                final User2 myAccount=new User2();
                User2 user=prefHelper.getUserInfo();

                String lockS=user.lockScreen;
                input.setText(user.email);
                //Log.e("lock",user.lockScreen);
                if(!lockS.contains("101")){
                    new AlertDialog.Builder(getContext())
                            .setTitle("Enter email")
                            .setView(vewInflater)
                            .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String newAbout = input.getText().toString();
                                    try {
                                        //Toast.makeText(getContext(), newAbout, Toast.LENGTH_SHORT).show();
                                        myAccount.email=newAbout;
                                        prefHelper.saveUserInfo(myAccount);
                                        dialogInterface.dismiss();
                                        Intent intent = new Intent(getContext(), settings.class);
                                        startActivity(intent);
                                    } catch (NullPointerException ne) {
                                        dialogInterface.dismiss();
                                        Toast.makeText(getContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {}
                                    dialogInterface.dismiss();

                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();

                                }
                            }).show();



                }
                else{
                    Intent intent = new Intent(getContext(), settings.class);
                    startActivity(intent);
                }
            }
        });
        view.findViewById(R.id.userDetailContainer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new com.mLabsCloseServer.LetsTalk.fragments.ProfileEditDialogFragment().show(getChildFragmentManager(), PROFILE_EDIT_TAG);
            }
        });
        view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.closeKeyboard(getContext(), view);
                dismiss();
            }
        });
        view.findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.openShareIntent(getContext(), null, String.format(getString(R.string.download_message), getString(R.string.app_name)));
            }
        });
//        view.findViewById(R.id.rate).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Helper.openPlayStore(getContext());
//            }
//        });
        view.findViewById(R.id.contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.openSupportMail(getContext());
            }
        });
//        view.findViewById(R.id.privacy).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new PrivacyPolicyDialogFragment().show(getChildFragmentManager(), PRIVACY_TAG);
//            }
//        });
        view.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmationDialogFragment confirmationDialogFragment = ConfirmationDialogFragment.newInstance(getString(R.string.logout_title),
                        getString(R.string.logout_message),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                FirebaseAuth.getInstance().signOut();
                                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(Helper.BROADCAST_LOGOUT));
                                sinchServiceInterface.stopClient();
                                helper.logout();
                                getActivity().finish();
                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                confirmationDialogFragment.show(getChildFragmentManager(), CONFIRM_TAG);
            }
        });
        return view;
    }

    public void setUserDetails() {
        helper = new Helper(getContext());
        userMe = helper.getLoggedInUser();
        userName.setText(userMe.getNameToDisplay());
        userStatus.setText(userMe.getStatus());
        Glide.with(this).load(userMe.getImage()).apply(new RequestOptions().placeholder(R.drawable.letstalk_placeholder)).into(userImage);
    }

    public static OptionsFragment newInstance(SinchService.SinchServiceInterface sinchServiceInterface) {
        OptionsFragment fragment = new OptionsFragment();
        fragment.sinchServiceInterface = sinchServiceInterface;
        return fragment;
    }
}
