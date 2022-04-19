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

public class Patientlog extends AppCompatActivity {

    Button hire,callpai;
    TextView cpname,cpage,cpacontact,schedule,pickup,dropat;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientlog);

        cpname=findViewById(R.id.p);
        cpage=findViewById(R.id.cpage);
        cpacontact=findViewById(R.id.mb);
        schedule=findViewById(R.id.schedule);
        pickup=findViewById(R.id.pickup);
        dropat=findViewById(R.id.dropat);
        hire=findViewById(R.id.direction);
        callpai=findViewById(R.id.callpatient);




        String cpass=getIntent().getStringExtra("pass").toString();
        readData(cpass);
    }

         private void readData(String cpass) {
             reference = FirebaseDatabase.getInstance().getReference("Patients");

             reference.child(cpass).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<DataSnapshot> task) {

                     if (task.isSuccessful()){

                             Toast.makeText(Patientlog.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                             DataSnapshot dataSnapshot = task.getResult();
                             String pname = String.valueOf(dataSnapshot.child("pname").getValue());
                             String page = String.valueOf(dataSnapshot.child("pAge").getValue());
                             String pmbno = String.valueOf(dataSnapshot.child("pmbno").getValue());
                            cpname.append(pname);
                            cpage.append(page);
                            cpacontact.append(pmbno);
                         callpai.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                String s="tel:"+pmbno;
                                 Intent i=new Intent(Intent.ACTION_DIAL);
                                 i.setData(Uri.parse(s));
                                 startActivity(i);
                             }
                         });
                                        }

                 }
             });
             reference.child(cpass).child("Main").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<DataSnapshot> task) {

                     if (task.isSuccessful()){

                         DataSnapshot dataSnapshot = task.getResult();
                         String pick = String.valueOf(dataSnapshot.child("Pickup").getValue());
                         String drop = String.valueOf(dataSnapshot.child("DropAt").getValue());
                         String date = String.valueOf(dataSnapshot.child("Date").getValue());
                         String time = String.valueOf(dataSnapshot.child("Time").getValue());
                        schedule.append("Date: "+date+"\nTime: "+time);
                        pickup.append(pick);
                        dropat.append(drop);
                     }

                 }
             });


        hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}