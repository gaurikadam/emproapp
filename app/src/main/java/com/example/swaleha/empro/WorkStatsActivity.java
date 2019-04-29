package com.example.swaleha.empro;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

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

public class WorkStatsActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    EditText yearedt;
    GridView gridView;
    Button btn;

    String yearvalue,janm,febm,marm,aprm,maym,junm,julm,augm,sepm,octm,novm,decm;
    int jansal=0,janc=0,finaljanabsent=0;
    int febsal=0,febc=0,finalfebabsent=0;
    int marsal=0,marc=0,finalmarabsent=0;
    int aprsal=0,aprc=0,finalaprabsent=0;
    int maysal=0,mayc=0,finalmayabsent=0;
    int junsal=0,junc=0,finaljunabsent=0;
    int julsal=0,julc=0,finaljulabsent=0;
    int augsal=0,augc=0,finalaugabsent=0;
    int sepsal=0,sepc=0,finalsepabsent=0;
    int octsal=0,octc=0,finaloctabsent=0;
    int novsal=0,novc=0,finalnovabsent=0;
    int decsal=0,decc=0,finaldecabsent=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_stats);

        yearedt = findViewById(R.id.yearedt);

        gridView = findViewById(R.id.gridv);
        btn = findViewById(R.id.showbtn);

        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(user.getUid());
    }

    public void btnclick(View v) {
        yearvalue = yearedt.getText().toString();
        final List<PersonalDetails> mDataDetails = getListData(yearvalue);
        gridView.setAdapter(new WorkStatsAdapter(this, mDataDetails));
    }

    private List<PersonalDetails> getListData(final String yearv) {


        final List<PersonalDetails> list = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().matches(".*"+yearv)) {
                        if (ds.getKey().matches(".*-01-.*")) {
                            janm = "Jan";
                            janc = janc+1;
                            Integer janval = new Integer(ds.getValue().toString());
                            if (janval==8) {
                                jansal += 1000;
                            }
                            else if(janval>=4 && janval<=7) {
                                jansal += 500;
                            }
                            else {
                                jansal += 0;
                            }
                        }
                    }
                }
                finaljanabsent = 31 - janc;
                PersonalDetails data = dataSnapshot.getValue(PersonalDetails.class);
                PersonalDetails pd = new PersonalDetails(data.getLeavesused(),data.getHolidays(),
                        data.getPendingsick(),data.getPendingcasual(),janc,finaljanabsent,jansal,janm);
                list.add(pd);

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().matches(".*"+yearv)) {
                        if (ds.getKey().matches(".*-02-.*")) {
                            febm = "Feb";
                            febc = febc+1;
                            Integer val = new Integer(ds.getValue().toString());
                            if (val==8) {
                                febsal += 1000;
                            }
                            else if(val>=4 && val<=7) {
                                febsal += 500;
                            }
                            else {
                                febsal += 0;
                            }
                        }
                    }
                }
                if (((new Integer(yearv))%400 == 0) || ((new Integer(yearv))%4 == 0) ) {
                    finalfebabsent = 29 - febc;
                    PersonalDetails febpd = new PersonalDetails(data.getLeavesused(),data.getHolidays(),
                            data.getPendingsick(),data.getPendingcasual(),febc,finalfebabsent,febsal,febm);
                    list.add(febpd);
                }
                else {
                    finalfebabsent = 28 - febc;
                    PersonalDetails febpd = new PersonalDetails(data.getLeavesused(),data.getHolidays(),
                            data.getPendingsick(),data.getPendingcasual(),febc,finalfebabsent,febsal,febm);
                    list.add(febpd);
                }

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().matches(".*"+yearv)) {
                        if (ds.getKey().matches(".*-03-.*")) {
                            marm = "March";
                            marc = marc+1;
                            Integer val = new Integer(ds.getValue().toString());
                            if (val==8) {
                                marsal += 1000;
                            }
                            else if(val>=4 && val<=7) {
                                marsal += 500;
                            }
                            else {
                                marsal += 0;
                            }
                        }
                    }
                }
                finalmarabsent = 31 - marc;
                PersonalDetails marpd = new PersonalDetails(data.getLeavesused(),data.getHolidays(),
                        data.getPendingsick(),data.getPendingcasual(),marc,finalmarabsent,marsal,marm);
                list.add(marpd);

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().matches(".*"+yearv)) {
                        if (ds.getKey().matches(".*-04-.*")) {
                            aprm = "April";
                            aprc = aprc+1;
                            Integer val = new Integer(ds.getValue().toString());
                            if (val==8) {
                                aprsal += 1000;
                            }
                            else if(val>=4 && val<=7) {
                                aprsal += 500;
                            }
                            else {
                                aprsal += 0;
                            }
                        }
                    }
                }
                finalaprabsent = 30 - aprc;
                PersonalDetails aprpd = new PersonalDetails(data.getLeavesused(),data.getHolidays(),
                        data.getPendingsick(),data.getPendingcasual(),aprc,finalaprabsent,aprsal,aprm);
                list.add(aprpd);

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().matches(".*"+yearv)) {
                        if (ds.getKey().matches(".*-05-.*")) {
                            maym = "May";
                            mayc = mayc+1;
                            Integer val = new Integer(ds.getValue().toString());
                            if (val==8) {
                                maysal += 1000;
                            }
                            else if(val>=4 && val<=7) {
                                maysal += 500;
                            }
                            else {
                                maysal += 0;
                            }
                        }
                    }
                }
                finalmayabsent = 31 - mayc;
                PersonalDetails maypd = new PersonalDetails(data.getLeavesused(),data.getHolidays(),
                        data.getPendingsick(),data.getPendingcasual(),mayc,finalmayabsent,maysal,maym);
                list.add(maypd);

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().matches(".*"+yearv)) {
                        if (ds.getKey().matches(".*-06-.*")) {
                            junm = "June";
                            junc = junc+1;
                            Integer val = new Integer(ds.getValue().toString());
                            if (val==8) {
                                junsal += 1000;
                            }
                            else if(val>=4 && val<=7) {
                                junsal += 500;
                            }
                            else {
                                junsal += 0;
                            }
                        }
                    }
                }
                finaljunabsent = 30 - junc;
                PersonalDetails junpd = new PersonalDetails(data.getLeavesused(),data.getHolidays(),
                        data.getPendingsick(),data.getPendingcasual(),junc,finaljunabsent,junsal,junm);
                list.add(junpd);

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().matches(".*"+yearv)) {
                        if (ds.getKey().matches(".*-07-.*")) {
                            julm = "July";
                            julc = julc+1;
                            Integer val = new Integer(ds.getValue().toString());
                            if (val==8) {
                                julsal += 1000;
                            }
                            else if(val>=4 && val<=7) {
                                julsal += 500;
                            }
                            else {
                                julsal += 0;
                            }
                        }
                    }
                }
                finaljulabsent = 31 - julc;
                PersonalDetails julpd = new PersonalDetails(data.getLeavesused(),data.getHolidays(),
                        data.getPendingsick(),data.getPendingcasual(),julc,finaljulabsent,julsal,julm);
                list.add(julpd);

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().matches(".*"+yearv)) {
                        if (ds.getKey().matches(".*-08-.*")) {
                            augm = "Aug";
                            augc = augc+1;
                            Integer val = new Integer(ds.getValue().toString());
                            if (val==8) {
                                augsal += 1000;
                            }
                            else if(val>=4 && val<=7) {
                                augsal += 500;
                            }
                            else {
                                augsal += 0;
                            }
                        }
                    }
                }
                finalaugabsent = 31 - augc;
                PersonalDetails augpd = new PersonalDetails(data.getLeavesused(),data.getHolidays(),
                        data.getPendingsick(),data.getPendingcasual(),augc,finalaugabsent,augsal,augm);
                list.add(augpd);

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().matches(".*"+yearv)) {
                        if (ds.getKey().matches(".*-09-.*")) {
                            sepm = "Sep";
                            sepc = sepc+1;
                            Integer val = new Integer(ds.getValue().toString());
                            if (val==8) {
                                sepsal += 1000;
                            }
                            else if(val>=4 && val<=7) {
                                sepsal += 500;
                            }
                            else {
                                sepsal += 0;
                            }
                        }
                    }
                }
                finalsepabsent = 30 - sepc;
                PersonalDetails seppd = new PersonalDetails(data.getLeavesused(),data.getHolidays(),
                        data.getPendingsick(),data.getPendingcasual(),sepc,finalsepabsent,sepsal,sepm);
                list.add(seppd);

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().matches(".*"+yearv)) {
                        if (ds.getKey().matches(".*-10-.*")) {
                            octm = "Oct";
                            octc = octc+1;
                            Integer val = new Integer(ds.getValue().toString());
                            if (val==8) {
                                octsal += 1000;
                            }
                            else if(val>=4 && val<=7) {
                                octsal += 500;
                            }
                            else {
                                octsal += 0;
                            }
                        }
                    }
                }
                finaloctabsent = 31 - octc;
                PersonalDetails octpd = new PersonalDetails(data.getLeavesused(),data.getHolidays(),
                        data.getPendingsick(),data.getPendingcasual(),octc,finaloctabsent,octsal,octm);
                list.add(octpd);

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().matches(".*"+yearv)) {
                        if (ds.getKey().matches(".*-11-.*")) {
                            novm = "Nov";
                            novc = novc+1;
                            Integer val = new Integer(ds.getValue().toString());
                            if (val==8) {
                                novsal += 1000;
                            }
                            else if(val>=4 && val<=7) {
                                novsal += 500;
                            }
                            else {
                                novsal += 0;
                            }
                        }
                    }
                }
                finalnovabsent = 30 - novc;
                PersonalDetails novpd = new PersonalDetails(data.getLeavesused(),data.getHolidays(),
                        data.getPendingsick(),data.getPendingcasual(),novc,finalnovabsent,novsal,novm);
                list.add(novpd);

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().matches(".*"+yearv)) {
                        if (ds.getKey().matches(".*-12-.*")) {
                            decm = "Dec";
                            decc = decc+1;
                            Integer val = new Integer(ds.getValue().toString());
                            if (val==8) {
                                decsal += 1000;
                            }
                            else if(val>=4 && val<=7) {
                                decsal += 500;
                            }
                            else {
                                decsal += 0;
                            }
                        }
                    }
                }
                finaldecabsent = 31 - decc;
                PersonalDetails decpd = new PersonalDetails(data.getLeavesused(),data.getHolidays(),
                        data.getPendingsick(),data.getPendingcasual(),decc,finaldecabsent,decsal,decm);
                list.add(decpd);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return list;
    }
}
