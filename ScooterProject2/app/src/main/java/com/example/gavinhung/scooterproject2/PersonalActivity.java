package com.example.gavinhung.scooterproject2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PersonalActivity extends AppCompatActivity {
    //Element
    String GenderSlelect,Age,CC,ClassSlelect,Verification;
    EditText PersonalActivityAge,PersonalActivityCC;
    Button PersonalActivitySubmitButton;
    TextView TextViewUser;
    TextView TextViewAge;
    TextView TextViewClass;
    TextView TextViewCC;



    //Firebase
    FirebaseUser user;
    FirebaseDatabase db;
    String UID;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);


        //Element
        PersonalActivityAge = (EditText) findViewById(R.id.PersonActivityAge);
        PersonalActivityCC = (EditText) findViewById(R.id.PersonalActivityCC);
        PersonalActivitySubmitButton = (Button) findViewById(R.id.PersonalActivitySubmitButton);
        TextViewUser = (TextView) findViewById(R.id.TextViewUser);
        TextViewAge = (TextView) findViewById(R.id.TextViewAge);
        TextViewClass = (TextView) findViewById(R.id.TextViewClass);
        TextViewCC = (TextView) findViewById(R.id.TextViewCC);


        //Firebase
        db = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        UID = user.getUid();
        auth=FirebaseAuth.getInstance();

        //ClassSpinner
        final Spinner Class = (Spinner) findViewById(R.id.ClassSpinner);
        ArrayAdapter<CharSequence> nAdapter1 = ArrayAdapter.createFromResource(
                this, R.array.personalClass, android.R.layout.simple_spinner_item );
        nAdapter1.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        Class.setAdapter(nAdapter1);

        //GenderSpinner Listener
        Class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ClassSlelect = Class.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //GenderSpinner
        final Spinner Gender = (Spinner) findViewById(R.id.GenderSpinner);
        ArrayAdapter<CharSequence> nAdapter2 = ArrayAdapter.createFromResource(
                this, R.array.personalInformationActivity, android.R.layout.simple_spinner_item );
        nAdapter2.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        Gender.setAdapter(nAdapter2);

        //GenderSpinner Listener
        Gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                GenderSlelect = Gender.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //PersonalActivitySubmitButton
        PersonalActivitySubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Age = PersonalActivityAge.getText().toString();
                CC = PersonalActivityCC.getText().toString();
                if(!Age.matches("")&!CC.matches("")){
                    DatabaseReference usersRef = db.getReference("Users");
                    usersRef.child(UID).child("個人資料").child("年齡").setValue(Age);
                    usersRef.child(UID).child("個人資料").child("車種").setValue(ClassSlelect);
                    usersRef.child(UID).child("個人資料").child("性別").setValue(GenderSlelect);
                    usersRef.child(UID).child("個人資料").child("車輛CC數").setValue(CC);
                    Toast.makeText(PersonalActivity.this,"提交完成",Toast.LENGTH_LONG).show();
                    ViewPersonal();
                }else{
                    Toast.makeText(PersonalActivity.this,"請輸入完整",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void ViewPersonal(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(UID).child("個人資料");;
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TextViewAge.setText(dataSnapshot.child("年齡").getValue().toString());
                TextViewUser.setText(auth.getCurrentUser().getEmail());
                TextViewClass.setText(dataSnapshot.child("車種").getValue().toString());
                TextViewCC.setText(dataSnapshot.child("車輛CC數").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {


            }
        });
    }


}
