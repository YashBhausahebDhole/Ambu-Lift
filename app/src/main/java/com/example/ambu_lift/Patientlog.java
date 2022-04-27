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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Patientlog extends AppCompatActivity {

    Button pickup,callpai;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientlog);


        callpai=findViewById(R.id.callpatient);
        pickup=findViewById(R.id.pickup);

        String cpass=getIntent().getStringExtra("mbno").toString();
        pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Patientlog.this, "Patient Confirmed", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Patientlog.this,MainActivity.class);
                startActivity(i);
            }
        });
        callpai.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                String s="tel:"+cpass;
                                 Intent i=new Intent(Intent.ACTION_DIAL);
                                 i.setData(Uri.parse(s));
                                 startActivity(i);
                             }
                         });
//        readData(cpass);
    }
//
//         private void readData(String cpass) {
//             reference = FirebaseDatabase.getInstance().getReference("Patients");
//
//             reference.child(cpass).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                 @Override
//                 public void onComplete(@NonNull Task<DataSnapshot> task) {
//
//                     if (task.isSuccessful()){
//
//                             Toast.makeText(Patientlog.this,"Successfully Read",Toast.LENGTH_SHORT).show();
//                             DataSnapshot dataSnapshot = task.getResult();
//                             String pname = String.valueOf(dataSnapshot.child("pname").getValue());
//                             String page = String.valueOf(dataSnapshot.child("pAge").getValue());
//                             String pmbno = String.valueOf(dataSnapshot.child("pmbno").getValue());
//                            cpname.append(pname);
//                            cpage.append(page);
//                            cpacontact.append(pmbno);
//
//                                        }
//
//                 }
//             });
//             reference.child(cpass).child("Booking").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                 @Override
//                 public void onComplete(@NonNull Task<DataSnapshot> task) {
//
//                     if (task.isSuccessful()){
//
//                         DataSnapshot dataSnapshot = task.getResult();
//                         String pick = String.valueOf(dataSnapshot.child("Pickup").getValue());
//                         String drop = String.valueOf(dataSnapshot.child("DropAt").getValue());
//                         String date = String.valueOf(dataSnapshot.child("Date").getValue());
//                         String time = String.valueOf(dataSnapshot.child("Time").getValue());
//                        schedule.append("Date: "+date+"\nTime: "+time);
//                        pickup.append(pick);
//                        dropat.append(drop);
//                     }
//
//                 }
//             });
//


    }
