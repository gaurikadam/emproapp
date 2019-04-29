package com.example.swaleha.empro;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {

    EditText edtold,edtnew,edtconfirm;
    Button changebtn;

    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edtold = findViewById(R.id.oldpass);
        edtnew = findViewById(R.id.newpass);
        edtconfirm = findViewById(R.id.confirmpass);
        changebtn = findViewById(R.id.changebtn);

        user = FirebaseAuth.getInstance().getCurrentUser();



        changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String newp = edtnew.getText().toString();
                final String conp = edtconfirm.getText().toString();

                AuthCredential credential = EmailAuthProvider
                        .getCredential(user.getEmail(), edtold.getText().toString());

                if (newp.equals(conp)) {

                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        user.updatePassword(newp).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(ChangePassword.this, "pass updated", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(ChangePassword.this, "pass not updated", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    } else {
                                        Toast.makeText(ChangePassword.this, "failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
                else
                    Toast.makeText(ChangePassword.this, "Password doesnt match", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
