package com.example.swaleha.empro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.swaleha.empro.model.PersonalDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    Button logoutbtn,changepassword;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    TextView edtname,edtqualify,edtcontact1,edtcontact2,edtemail,edtdob,edtbankname,edtaddress,edtbenef,edtaccno,edtifsc,edtbranch,edttopname,edttoppost;
    String name,qualify,contact1,contact2,emailid,dob,bankname,address,benef,accno,ifsc,branch,topname,toppost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(user.getUid());

        edtname = findViewById(R.id.name_tv);
        name = edtname.getText().toString();

        edtqualify = findViewById(R.id.qualify_tv);
        qualify = edtqualify.getText().toString();

        edtcontact1 = findViewById(R.id.contact1_tv);
        contact1 = edtcontact1.getText().toString();

        edtcontact2 = findViewById(R.id.contact2_tv);
        contact2 = edtcontact2.getText().toString();

        edtemail = findViewById(R.id.email_tv);
        emailid = edtemail.getText().toString();

        edtdob = findViewById(R.id.dob_tv);
        dob = edtdob.getText().toString();

        edtbankname = findViewById(R.id.bnkname_tv);
        bankname = edtbankname.getText().toString();

        edtaddress = findViewById(R.id.address_tv);
        address = edtaddress.getText().toString();

        edtbenef = findViewById(R.id.benefit_tv);
        benef = edtbenef.getText().toString();

        edtaccno = findViewById(R.id.accno_tv);
        accno = edtaccno.getText().toString();

        edtifsc = findViewById(R.id.ifsc_tv);
        ifsc = edtifsc.getText().toString();

        edtbranch = findViewById(R.id.branch_tv);
        branch = edtbranch.getText().toString();

        edttopname = findViewById(R.id.topname);
        topname = edttopname.getText().toString();

        edttoppost = findViewById(R.id.toppost);
        toppost = edttoppost.getText().toString();

        logoutbtn = findViewById(R.id.profilelogout);
        changepassword = findViewById(R.id.changepass);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this,MainActivity.class));
            }
        });

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,ChangePassword.class));
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PersonalDetails mDataValue = dataSnapshot.getValue(PersonalDetails.class);
                edtname.setText(mDataValue.getName());
                edttopname.setText(mDataValue.getName());
                edtcontact1.setText(Integer.toString(mDataValue.getContact1()));
                edtcontact2.setText(Integer.toString(mDataValue.getContact2()));
                edtemail.setText(mDataValue.getEmailid());
                edtqualify.setText(mDataValue.getQualification());
                edtbankname.setText(mDataValue.getBankname());
                edtdob.setText(mDataValue.getDob());
                edtifsc.setText(mDataValue.getIfscno());
                edtaddress.setText(mDataValue.getAddress());
                edtbenef.setText(mDataValue.getBeneficiary());
                edtaccno.setText(mDataValue.getAccno());
                edtbranch.setText(mDataValue.getBranch());
                edttoppost.setText(mDataValue.getPost());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
