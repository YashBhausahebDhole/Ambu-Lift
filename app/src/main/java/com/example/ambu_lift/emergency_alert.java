package com.example.ambu_lift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

    TextView p,mb,si;
    Button callpatient,direction;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_alert);
        
        p=findViewById(R.id.p);
        mb=findViewById(R.id.mb);
        si=findViewById(R.id.si);
        direction=findViewById(R.id.pickup);
        callpatient=findViewById(R.id.callpatient);
        String aname=getIntent().getStringExtra("name").toString();
        readData(aname);
        
    }

    private void readData(String aname) {
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
                    p.append(pname);
                    mb.append(pmbno);
                    si.append(psit);
                    callpatient.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String s="tel:"+pmbno;
                            Intent i=new Intent(Intent.ACTION_DIAL);
                            i.setData(Uri.parse(s));
                            startActivity(i);
                        }
                    });

                    direction.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            Intent i=new Intent(emergency_alert.this,directions.class);
//                            i.putExtra("Lati",lati);
//                            i.putExtra("long",longi);
//                            startActivity(i);
                        }
                    });
                }

            }
        });
    }
}