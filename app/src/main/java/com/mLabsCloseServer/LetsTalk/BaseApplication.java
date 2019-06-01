package com.mLabsCloseServer.LetsTalk;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

//import com.crashlytics.android.Crashlytics;

import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.google.GoogleEmojiProvider;
import com.mLabsCloseServer.LetsTalk.receivers.ConnectivityReceiver;

//import io.fabric.sdk.android.Fabric;

/**
 * Created by mayank on 11/2/17.
 */

public class BaseApplication extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ConnectivityReceiver.init(this);
//        Fabric.with(this, new Crashlytics());
        EmojiManager.install(new GoogleEmojiProvider());

        String admobAppId = getString(R.string.admob_app_id);

    }

}
