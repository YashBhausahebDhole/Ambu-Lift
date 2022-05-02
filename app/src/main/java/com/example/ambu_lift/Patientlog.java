package com.example.ambu_lift;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class Patientlog extends AppCompatActivity {

    Button pickup,callpai;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientlog);


        callpai = findViewById(R.id.callpatient);
        pickup = findViewById(R.id.cnpa);
        String dname = getIntent().getStringExtra("dname").toString();
        String mbno = getIntent().getStringExtra("mbn").toString();
        String cpass = getIntent().getStringExtra("mbno").toString();
        pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Patientlog.this, "Patient Confirmed", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Patientlog.this, MainActivity.class);
                startActivity(i);
            }
        });
        callpai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "tel:" + cpass;
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse(s));
                startActivity(i);
            }
        });
        pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "Dear Patient,\nYou will be pickup by from your given pickup address and at given schedule by ambulance driver " + dname + " with contact no: " + mbno + "\nvery soon \nAmbuLift";

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(cpass, null, msg, null, null);
                    Toast.makeText(getApplicationContext(), "Message Sent to Patient", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Some Error occurred", Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent(Patientlog.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    }
