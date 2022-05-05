package com.example.ambu_lift;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class Patientlog extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    Button pickup,callpai;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientlog);


        callpai = findViewById(R.id.callpatient);
        pickup = findViewById(R.id.cnpa);

        String drivername = getIntent().getStringExtra("name").toString();
        String dmobile = getIntent().getStringExtra("mobile").toString();
        String mobile = getIntent().getStringExtra("mbno").toString();
        readData(drivername,dmobile,mobile);



        callpai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "tel:" + mobile;
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse(s));
                startActivity(i);
            }
        });

    }

    private void readData(String drivername, String dmobile,String mobile) {
        pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMSMessage();
                String msg1 = ("Dear Patient,\nYou will be pickup by from your given pickup address  by ambulance driver "+drivername+" with contact no: "+dmobile+"\n at given schedule \nAmbuLift");

                try {
                    SmsManager smManager = SmsManager.getDefault();
                    smManager.sendTextMessage(mobile, null, msg1, null, null);
                    Toast.makeText(getApplicationContext(), "Message Sent to Patient", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Some Error occurred", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    protected void sendSMSMessage() {
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
            Toast.makeText(Patientlog.this, "Patient Confirmed", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Patientlog.this, MainActivity.class);
            startActivity(i);
        }
    }

}
