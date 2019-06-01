package com.mLabsCloseServer.LetsTalk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.amirarcane.lockscreen.activity.EnterPinActivity;
import com.mLabsCloseServer.LetsTalk.R;
import com.mLabsCloseServer.LetsTalk.models.User2;
import com.mLabsCloseServer.LetsTalk.utils.SharedPreferenceHelper;


public class settings extends AppCompatActivity {

    SwitchCompat switchCompat;
    SwitchCompat switchCompatFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        final SharedPreferenceHelper prefHelper = SharedPreferenceHelper.getInstance(getApplicationContext());

        Button fab = (Button) findViewById(R.id.ChangePin);
        Button changeFaceID = findViewById(R.id.ChangeFaceLock);

        switchCompat=(SwitchCompat)findViewById(R.id.switchButton);
        switchCompatFace = findViewById(R.id.switchFaceButton);

        User2 myAccount = prefHelper.getUserInfo();

        if(myAccount.lockScreen.equals("101")) {
            switchCompat.toggle();
            fab.setVisibility(View.VISIBLE);
            fab.setEnabled(true);
        }else{
            fab.setVisibility(View.INVISIBLE);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = EnterPinActivity.getIntent(getApplicationContext(), true);
                startActivity(intent);
            }
        });

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    User2 myAccount=new User2();
                    myAccount.lockScreen = "101";
                    prefHelper.lockinfoedit(myAccount);
                    Intent intent = EnterPinActivity.getIntent(getApplicationContext(), true);
                    startActivity(intent);
                }else{
                    User2 myAccount=new User2();
                    myAccount.lockScreen = "0";
                    prefHelper.lockinfoedit(myAccount);
                }
            }
        });


        // Face Lock
        if (myAccount.facedetection.equals("102")) {
            switchCompatFace.toggle();
            changeFaceID.setVisibility(View.VISIBLE);
            changeFaceID.setEnabled(true);
        }else{
            changeFaceID.setVisibility(View.INVISIBLE);
        }

        changeFaceID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Todo... Intent it to face capture activity
            }
        });

        switchCompatFace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    User2 myAccount=new User2();
                    myAccount.facedetection = "102";
                    prefHelper.lockinfoedit(myAccount);

                    // Todo... Intent it to face capture activity
                    Intent intent = EnterPinActivity.getIntent(getApplicationContext(), true);
                    startActivity(intent);

                }else{

                    User2 myAccount=new User2();
                    myAccount.facedetection = "0";
                    prefHelper.lockinfoedit(myAccount);
                }
            }
        });


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}