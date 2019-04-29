package com.example.swaleha.empro;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class timer extends Service {
    public static String str_receiver = "com.example.swaleha.empro";
    Timer T=new Timer();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    Calendar cal = Calendar.getInstance();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    int count;

    @Override
    public void onCreate() {
        super.onCreate();
        //Log.d("service","createeed");
        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(user.getUid());

        int delay = 5000; // delay for 5 sec.
        int period = 1000; // repeat every sec.
        count =0;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                // Your code
                count++;

            }
        }, delay, period);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Log.d("service",String.valueOf(count));
//        Intent secon = new Intent(timer.this, Main2Activity.class);
//        intent.putExtra("sec","ssssssssss");
        //hours = count/3,600
//        Intent secon = new Intent(timer.this, MainActivity.class);
//        secon.putExtra("sec", String.valueOf(count));
        Integer no_of_hours = new Integer(count / 3600);
        //no_of_hours = count / 3600 ;
        databaseReference.child(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()).toString()).setValue(no_of_hours);

    }
//    public void fnupdate()
//    {

    //sendBroadcast(intent);
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public boolean stopService(Intent name) {
        //Log.d("service","stoooooooooooooop");

        return super.stopService(name);
    }

    @Override
    public ComponentName startService(Intent service) {
        return super.startService(service);
    }
}

