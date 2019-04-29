package com.example.swaleha.empro;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.swaleha.empro.model.PersonalDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        final String username="gauribkadam@gmail.com";
        final String pass = "gauribkadam";
        final String name = "Gauri kadam";
        final String qualification = "BE";
        final String emailid = "gauri98@gmail.com";
        final String dob = "18/8/1998";
        final String address = "Airoli";
        final String bankname = "Maharashtra";
        final String beneficiary = "Me";
        final String accno = "123567800";
        final String ifscno = "14523";
        final String branch = "Thane";
        final String post = "Software Engineer";
        final int contact1 = 123456888;
        final int contact2 = 1234568899;
        final int pendingpaid = 1;
        final int pendingsick = 2;
        final int pendingcasual = 5;



//        firebaseAuth.createUserWithEmailAndPassword(username, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//
//                    PersonalDetails add = new PersonalDetails( username, pass, name, contact1, contact2, qualification, emailid,
//                             dob, address, bankname, beneficiary, accno, ifscno, branch,
//                             post, pendingpaid, pendingsick, pendingcasual);
//
//                    FirebaseDatabase.getInstance().getReference()
//                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                            .setValue(add).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//
//                            if (task.isSuccessful()) {
//                                Toast.makeText(RegisterActivity.this, "Women registered", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                } else {
//                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }
}
