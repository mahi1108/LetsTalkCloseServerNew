package com.mLabsCloseServer.LetsTalk.serverType;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mLabsCloseServer.LetsTalk.R;
import com.mLabsCloseServer.LetsTalk.activities.SignInActivity;
import com.mLabsCloseServer.LetsTalk.models.Dept;
import com.mLabsCloseServer.LetsTalk.models.Organization;
import com.mLabsCloseServer.LetsTalk.models.Organizations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CLoseServerInviteCode extends AppCompatActivity {

    private Spinner spinnerOrg;
    private Spinner spinnerDept;
    private Button btnApply;

    private String selectedOrg;
    private String selectedDept;

    private DatabaseReference mDatabaseRef;
    // private LovelyProgressDialog waitingDialog;
    final List<String> orgList = new ArrayList<String>();
    final List<String> deptList = new ArrayList<String>();

    String selected_org_code = "";
    int license_points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_server_invite_code);


        spinnerOrg = (Spinner) findViewById(R.id.spinnerOrg);
        spinnerDept = (Spinner) findViewById(R.id.spinnerDept);

        btnApply = (Button) findViewById(R.id.reg_submit);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();


        mDatabaseRef.child("closeServer").child("orgList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot orgItem : dataSnapshot.getChildren()) {
                    String orgName = orgItem.getValue().toString();

                    orgList.add(orgName);
                }

                ArrayAdapter<String> orgAdapter = new ArrayAdapter<String>(CLoseServerInviteCode.this, android.R.layout.simple_spinner_item, orgList);
                orgAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerOrg.setAdapter(orgAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

     /*   spinnerOrg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        }); */

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedOrg = spinnerOrg.getSelectedItem().toString();
                selectedDept = spinnerDept.getSelectedItem().toString();
                String entered_org_code = ((EditText)findViewById(R.id.security_invite_code)).getText().toString();
                try {
                    if (!selectedOrg.equals("") && !selectedDept.equals("") &&
                                entered_org_code.equals(selected_org_code) &&
                                license_points > 0) {
                      Intent resultIntent = new Intent(
                                CLoseServerInviteCode.this,
                                SignInActivity.class);
                        resultIntent.putExtra("selectedOrg", selectedOrg);  // put data that you want returned to activity A
                        resultIntent.putExtra("selectedDept", selectedDept);
                        //setResult(Activity.RESULT_OK, resultIntent);
                        startActivity(resultIntent);
                        finish();
                    }else{
                        if(license_points<0){
                            Toast.makeText(getApplicationContext(),
                                    "License points are exceeded for selected Organization",
                                    Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "Select Organaztion,Department and enter Correct security code",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),
                            "Select Organaztion,Department and enter Correct security code",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

            loadOrganizationsAndDepts();
    }

        public  void loadOrganizationsAndDepts(){
                FirebaseDatabase dBase = FirebaseDatabase.getInstance();
                DatabaseReference ref  =  dBase.getReference("organizations");
                final Organizations organizations = new Organizations();

            ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<Organization> list_orgs = new ArrayList<>();
                        Iterable<DataSnapshot> it_orgs= dataSnapshot.getChildren();
                        Iterator<DataSnapshot> itr_orgs = it_orgs.iterator();
                          // to read the child elements of Organizations
                        while (itr_orgs.hasNext()){
                            Organization organization = new Organization();

                            DataSnapshot ds_org = itr_orgs.next();
                            Iterable<DataSnapshot> it_org=ds_org.getChildren();
                            Iterator<DataSnapshot> itr_org = it_org.iterator();
                            // to read the child elements of Organizations>>Organization
                            while (itr_org.hasNext()){
                                    DataSnapshot ds = itr_org.next();
                                     switch (ds.getKey()){
                                         case "license_points":
                                             int points = Integer.valueOf(ds.getValue().toString());
                                             organization.setLicense_points(Integer.valueOf(points));
                                             break;
                                         case "name":
                                             organization.setName(ds.getValue().toString());
                                             break;
                                         case "security_code":
                                             organization.setSecurity_code(ds.getValue().toString());
                                             break;
                                         case "departments":

                                             ArrayList<Dept> depts = new ArrayList<>();

                                            Iterable<DataSnapshot> itr_depts =  ds.getChildren();
                                            Iterator<DataSnapshot> it_depts = itr_depts.iterator();
                                            while (it_depts.hasNext()){
                                                Dept dept = new Dept();

                                                DataSnapshot ds_dept =     it_depts.next();

                                                Iterable<DataSnapshot> itr_dept = ds_dept.getChildren();
                                                Iterator<DataSnapshot> it_dept = itr_dept.iterator();
                                                while (it_dept.hasNext()){
                                                    DataSnapshot dept_ds = it_dept.next();
                                                    if(dept_ds.getKey().equals("name")){
                                                                dept.setName(dept_ds.getValue().toString());
                                                    }
                                                }
                                                depts.add(dept);
                                             }

                                              organization.setDepts(depts);

                                             break;
                                     }
                            }
                            list_orgs.add(organization);
                        }
                        organizations.setOrganizations(list_orgs);

                        List<String> orgs_list = new ArrayList<>();
                        for(int i=0;i<organizations.getOrganizations().size();i++){
                            orgs_list.add(organizations.getOrganizations().get(i).getName());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                CLoseServerInviteCode.this,
                                android.R.layout.simple_list_item_single_choice,orgs_list);
                        spinnerOrg.setAdapter(adapter);

                        spinnerOrg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                String selname = spinnerOrg.getSelectedItem().toString();

                                for(Organization org:organizations.getOrganizations())
                                {
                                        if(org.getName().equals(selname)){
                                         selected_org_code = org.getSecurity_code();
                                          license_points = org.getLicense_points();
                                          List<Dept>  depts_list = org.getDepts();
                                          ArrayList<String> dept_names = new ArrayList<>();
                                          for(Dept dep:depts_list){
                                              dept_names.add(dep.getName());
                                          }

                                          ArrayAdapter<String> dept_adapter = new ArrayAdapter<String>(
                                                  CLoseServerInviteCode.this,
                                                  android.R.layout.simple_list_item_single_choice,dept_names);
                                            spinnerDept.setAdapter(dept_adapter);

                                        }
                                   }
                              }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                      }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

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

                ArrayAdapter<String> deptAdapter = new ArrayAdapter<String>(CLoseServerInviteCode.this, android.R.layout.simple_spinner_item, deptList);
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
