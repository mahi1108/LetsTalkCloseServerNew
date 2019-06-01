package com.mLabsCloseServer.LetsTalk.serverType;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mLabsCloseServer.LetsTalk.R;

import java.util.ArrayList;
import java.util.List;

public class CloseServerReg extends AppCompatActivity {

    private Spinner spinnerOrg;
    private Spinner spinnerDept;
    private Button btnApply;

    private String selectedOrg;
    private String selectedDept;

    private DatabaseReference mDatabaseRef;
   // private LovelyProgressDialog waitingDialog;
    final List<String> orgList = new ArrayList<String>();
    final List<String> deptList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_server_reg);



        spinnerOrg = (Spinner) findViewById(R.id.spinnerOrg);
        spinnerDept = (Spinner) findViewById(R.id.spinnerDept);

        btnApply = (Button) findViewById(R.id.btnApply);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();


        mDatabaseRef.child("closeServer").child("orgList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot orgItem : dataSnapshot.getChildren()) {
                    String orgName = orgItem.getValue().toString();

                    orgList.add(orgName);
                }

                ArrayAdapter<String> orgAdapter = new ArrayAdapter<String>(CloseServerReg.this, android.R.layout.simple_spinner_item, orgList);
                orgAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerOrg.setAdapter(orgAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        spinnerOrg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    deptList.clear();



                    selectedOrg = parent.getItemAtPosition(position).toString();

                    loadDept(parent.getItemAtPosition(position).toString());

                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (!selectedOrg.equals("") && !selectedDept.equals("")) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("selectedOrg", selectedOrg);  // put data that you want returned to activity A
                        resultIntent.putExtra("selectedDept", selectedDept);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Select Organaztion and Department", Toast.LENGTH_LONG).show();


                }

            }
        });


    }

    private void loadDept(String orgName) {

        mDatabaseRef.child("closeServer").child("org").child(orgName).child("departmentList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot deptItem : dataSnapshot.getChildren()) {

                    String deptName = deptItem.getValue().toString();

                    deptList.add(deptName);
                }

                ArrayAdapter<String> deptAdapter = new ArrayAdapter<String>(CloseServerReg.this, android.R.layout.simple_spinner_item, deptList);
                deptAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDept.setAdapter(deptAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        spinnerDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDept = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
