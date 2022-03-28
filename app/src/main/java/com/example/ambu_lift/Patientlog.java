package com.example.ambu_lift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    Button hire,viewpai;
    TextView cpname,cpage,cpacontact,schedule,pickup,dropat;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientlog);

        cpname=findViewById(R.id.cpname);
        cpage=findViewById(R.id.cpage);
        cpacontact=findViewById(R.id.cpacontact);
        schedule=findViewById(R.id.schedule);
        pickup=findViewById(R.id.pickup);
        dropat=findViewById(R.id.dropat);
        hire=findViewById(R.id.hire);
        viewpai=findViewById(R.id.viewpai);


     viewpai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pcpass = "Atharva123";
                String pname="Atharva Mundhe";
                    readData(pcpass,pname);
                              }

         private void readData(String pcpass,String pname) {
             reference = FirebaseDatabase.getInstance().getReference("Patients");
             reference.child(pcpass).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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
                                        }

                 }
             });
             reference.child(pname).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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

         }
     });
        hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}