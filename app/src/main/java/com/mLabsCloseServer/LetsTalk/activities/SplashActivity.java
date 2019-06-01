package com.mLabsCloseServer.LetsTalk.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mLabsCloseServer.LetsTalk.R;
import com.mLabsCloseServer.LetsTalk.serverType.CLoseServerInviteCode;
import com.mLabsCloseServer.LetsTalk.utils.Helper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Helper helper = new Helper(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             //   startActivity(new Intent(SplashActivity.this, helper.getLoggedInUser() != null ? MainActivity.class : SignInActivity.class));
                startActivity(new Intent(SplashActivity.this, helper.getLoggedInUser() != null ? MainActivity.class : CLoseServerInviteCode.class));
                finish();
            }
        }, 1500);
    }
}
