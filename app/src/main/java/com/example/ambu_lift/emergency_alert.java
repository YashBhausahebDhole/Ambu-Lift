package com.example.ambu_lift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class emergency_alert extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    TextView p,mb,si;
    Button callpatient,conp;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_alert);
        
        p=findViewById(R.id.p);
        mb=findViewById(R.id.mb);
        si=findViewById(R.id.si);
        conp=findViewById(R.id.cnpa);
        callpatient=findViewById(R.id.callpatient);
        String aname=getIntent().getStringExtra("name").toString();
        String dname=getIntent().getStringExtra("dname").toString();
        String mbno=getIntent().getStringExtra("mbno").toString();

        readData(aname,dname,mbno);
        
    }

    private void readData(String aname,String dname,String mbno) {
        reference = FirebaseDatabase.getInstance().getReference("Emergency");

        reference.child(aname).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    Toast.makeText(emergency_alert.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                    DataSnapshot dataSnapshot = task.getResult();
                    String pname = String.valueOf(dataSnapshot.child("PatientName").getValue());
                    String pmbno = String.valueOf(dataSnapshot.child("MobileNo").getValue());
                    String psit = String.valueOf(dataSnapshot.child("Situation").getValue());
                    String lati = String.valueOf(dataSnapshot.child("Latitude").getValue());
                    String longi = String.valueOf(dataSnapshot.child("Longitude").getValue());
//
                    p.append(pname);
                    mb.append(pmbno);
                    si.append(psit);
                    callpatient.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String s="tel:"+pmbno;
                            Intent i=new Intent(Intent.ACTION_CALL);
                            i.setData(Uri.parse(s));
                            startActivity(i);
                        }
                    });
                    conp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sendSMSMessage(lati,longi,aname);
                            String msg=("Dear Concern,\nYou will be pickup by ambulance driver "+dname+" with contact no: "+mbno+"\nvery soon \nAmbuLift");

                            try {
                                SmsManager smsManager=SmsManager.getDefault();
                                smsManager.sendTextMessage(pmbno,null,msg,null,null);
                                Toast.makeText(getApplicationContext(),"Message Sent",Toast.LENGTH_LONG).show();
                            }catch (Exception e)
                            {
                                Toast.makeText(getApplicationContext(),"Some Error occurred",Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });
    }

    protected void sendSMSMessage(String lati,String longi,String aname) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        else {
            FirebaseDatabase.getInstance().getReference().child("Emergency").child(aname).removeValue();
            Intent i=new Intent(emergency_alert.this,drpact.class);
            i.putExtra("Lati",lati);
            i.putExtra("Long",longi);
            startActivity(i);

        }
    }
}