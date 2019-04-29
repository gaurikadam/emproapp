package com.example.swaleha.empro;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button loginbuttn;
    EditText username,pass;
   //;. private static final String TAG = "EmailPassword";
    private ProgressDialog pd;

    // Alert Dialog Manager
//    AlertDialogManager alert = new AlertDialogManager();
//
//    // Session Manager Class
//    SessionManager session;

//    public static final String MyPref = "" ;
//    String novalue = new String("");
//    SharedPreferences sharedpreferences;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginbuttn = findViewById(R.id.loginbtn);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);

        mAuth = FirebaseAuth.getInstance();
//        sharedpreferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
//        final SharedPreferences.Editor editor = sharedpreferences.edit();
        // Session Manager
//        session = new SessionManager(getApplicationContext());
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Please Wait...");

        if(mAuth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), Main2Activity.class));
        }

//        if (MyPref.equals(novalue)) {
//            final String email = username.getText().toString();
//            String password = pass.getText().toString();
//
//            mAuth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                if(pd.isShowing())
//                                    pd.dismiss();
//                                // Sign in success, update UI with the signed-in user's information
//                                //Log.d("abc", "signInWithEmail:success");
//                                //PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("userId",email).apply();
//
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                startActivity(new Intent(MainActivity.this,Main2Activity.class));
//                                editor.putString(MyPref, user.getUid());
////                            session.createLoginSession(user.getUid().toString(), email);
////                            Log.d("tagged",Integer.toString(session.PRIVATE_MODE));
////                            updateUI(user);
//                            } else {
//                                // If sign in fails, display a message to the user.
//                                //Log.w("def", "signInWithEmail:failure", task.getException());
//                                Toast.makeText(MainActivity.this, "Authentication failed.",
//                                        Toast.LENGTH_SHORT).show();
////                            updateUI(null);
//                            }
//
//                            // ...
//                        }
//                    });
//        }
//        else {
//            startActivity(new Intent(MainActivity.this,Main2Activity.class));
//        }

        loginbuttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                login();
            }
        });
    }

    public void login() {
        final String email = username.getText().toString();
        String password = pass.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if(pd.isShowing())
                                pd.dismiss();
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d("abc", "signInWithEmail:success");
                            //PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("userId",email).apply();
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(MainActivity.this,Main2Activity.class));
//                            session.createLoginSession(user.getUid().toString(), email);
//                            Log.d("tagged",Integer.toString(session.PRIVATE_MODE));
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w("def", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

}
