package com.example.swaleha.empro;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.swaleha.empro.model.PersonalDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LocationListener {

    ImageButton in,out;
    String ssid;
    String ourssid = "SHAIKH";

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    LocationManager locationManager;
    String provider;
    Location location;
    Double mLat, mLong;
    int a=0,count=0;

    Calendar cal = Calendar.getInstance();
    int radius=700,distance;

    TextView gmname,whintime,whouttime,whdate,gmdate,gmtxt,saltxt,daysworked,daysabsent,sickleaves,casualleaves,holidays,leavesused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //mycode
//        session = new SessionManager(getApplicationContext());
//        session.checkLogin();

        in = findViewById(R.id.inbtn);
        out = findViewById(R.id.outbtn);
        final double latitude = 19.0177;
        final double longitude = 73.0247;

//        in.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_service = new Intent(getApplicationContext(),timer.class);
//                startService(intent_service);
//            }
//        });
//
//        out.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_service = new Intent(getApplicationContext(),timer.class);
//                stopService(intent_service);
//            }
//        });
        gmtxt = findViewById(R.id.gmtext);
        gmname = findViewById(R.id.good_tv);
        whintime = findViewById(R.id.time_tv);
        whouttime = findViewById(R.id.outdate_tv);
        whdate = findViewById(R.id.date1_tv);
        gmdate = findViewById(R.id.date_tv);
        saltxt = findViewById(R.id.salarytxt);
        daysworked = findViewById(R.id.daysworked_tv);
        daysabsent = findViewById(R.id.daysabsent_tv);
        leavesused = findViewById(R.id.leave_used_tv);
        holidays = findViewById(R.id.holidays_tv);
        sickleaves = findViewById(R.id.sick_leaves_tv);
        casualleaves = findViewById(R.id.casual_leaves_tv);

        int timeOfDay = cal.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay >= 0 && timeOfDay < 12){
            gmtxt.setText("Good Morning,");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            gmtxt.setText("Good Afternoon,");
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            gmtxt.setText("Good Evening,");
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            gmtxt.setText("Good Night,");
        }

        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PersonalDetails mDataValue = dataSnapshot.getValue(PersonalDetails.class);
                gmname.setText(mDataValue.getName());
                sickleaves.setText(mDataValue.getPendingsick()+"");
                casualleaves.setText(mDataValue.getPendingcasual()+"");
                holidays.setText(mDataValue.getHolidays()+"");
                leavesused.setText(mDataValue.getLeavesused()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        whdate.setText(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()).toString());
        gmdate.setText(new SimpleDateFormat("MMM").format(cal.getTime()).toString()+
                " 01 to "+new SimpleDateFormat("MMM dd yyyy").format(cal.getTime()).toString());

        final String currentdate = new SimpleDateFormat("MM-yyyy").format(cal.getTime()).toString();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().matches(".*"+currentdate)) {
                        count = count+1;
                        Integer b = new Integer(ds.getValue().toString());
                        if (b==8) {
                            a += 1000;
                        }
                        else if(b>=4 && b<=7) {
                            a += 500;
                        }
                        else {
                            a += 0;
                        }
                    }


                }
                saltxt.setText(""+a);
                daysworked.setText(count+"");

                String datetominus = new SimpleDateFormat("MM").format(cal.getTime()).toString();
                Integer datetominusint = new Integer(datetominus);
                int finaldaysabsent = datetominusint - count;
                daysabsent.setText(finaldaysabsent+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //System.out.println(new SimpleDateFormat("MMM").format(cal.getTime()));

//        final double latitudetwo = 19.063571;
//        final double longitudetwo = 73.013893;

//        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        final WifiInfo wifiInfo = wifiManager.getConnectionInfo();


        // Getting LocationManager object
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Creating an empty criteria object
        Criteria criteria = new Criteria();

        // Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, false);

        if (provider != null && !provider.equals("")) {

            // Get the location from the given provider
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            location = locationManager.getLastKnownLocation(provider);

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            locationManager.requestLocationUpdates(provider, 20000, 1, this);
            if (location != null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
        }

        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Main2Activity.this,"Please be in office premises",Toast.LENGTH_SHORT).show();
//                if (WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState()) == NetworkInfo.DetailedState.CONNECTED) {
//                }
//
                whintime.setText(new SimpleDateFormat("HH:mm:ss").format(cal.getTime()).toString());

                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                if(wifiInfo.getSupplicantState()== SupplicantState.COMPLETED)
                {
                    ssid = wifiInfo.getSSID();
                }
                else
                    Toast.makeText(getApplicationContext(),"Wifi not connected",Toast.LENGTH_SHORT).show();

                if (location != null) {
                    Location locationA = new Location("point A");

                    locationA.setLatitude(latitude);
                    locationA.setLongitude(longitude);

                    Location locationB = new Location("point B");

                    locationB.setLatitude(mLat);
                    locationB.setLongitude(mLong);

                    distance = (int) locationA.distanceTo(locationB);
//                String d = new Float(distance).toString();
//                Log.d("distance",d);
                    if (!((ssid.equals(ourssid)) && (distance <= radius))) {
                        //Log.d("hieeeeee","yes");
                        Intent intent_service = new Intent(getApplicationContext(),timer.class);
                        startService(intent_service);

                    }
                    else {
                        Toast.makeText(Main2Activity.this,"Please be in office premises",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(getApplicationContext(),"Location showing null",Toast.LENGTH_SHORT).show();


                //Log.d("heloooooo","h");
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whouttime.setText(new SimpleDateFormat("HH:mm:ss").format(cal.getTime()).toString());
                Intent intent_service = new Intent(getApplicationContext(),timer.class);
                stopService(intent_service);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action

        } else if (id == R.id.holidays) {
            Intent i = new Intent(Main2Activity.this,HolidayActivity.class);
            startActivity(i);

        } else if (id == R.id.workstats) {
            Intent i = new Intent(Main2Activity.this,WorkStatsActivity.class);
            startActivity(i);

        } else if (id == R.id.profile) {
            Intent i = new Intent(Main2Activity.this,ProfileActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logoubtnlistener(View v) {
//        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
//        startActivity(intent);
//        Log.d("one","before");
        FirebaseAuth.getInstance().signOut();
//        Log.d("two","after");
        startActivity(new Intent(Main2Activity.this,MainActivity.class));
    }

    @Override
    public void onLocationChanged(Location location) {
        mLong = location.getLongitude();
        mLat = location.getLatitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    public void abc(View v) {
        Intent intent_service = new Intent(getApplicationContext(),timer.class);
        startService(intent_service);
    }
}
