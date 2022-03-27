package com.example.ambu_lift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ambu_lift.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

                String username = "Atharva Mundhe";
                if (!username.isEmpty()){

                    readData(username);
                }else{

                    Toast.makeText(Patientlog.this,"PLease Enter Username",Toast.LENGTH_SHORT).show();
                }

            }

         private void readData(String username) {
             reference = FirebaseDatabase.getInstance().getReference("Patients");
             reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<DataSnapshot> task) {

                     if (task.isSuccessful()){

                         if (task.getResult().exists()){

                             Toast.makeText(Patientlog.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                             DataSnapshot dataSnapshot = task.getResult();
                             String pname = String.valueOf(dataSnapshot.child("pname").getValue());
                             String page = String.valueOf(dataSnapshot.child("pAge").getValue());
                             String pmbno = String.valueOf(dataSnapshot.child("pmbno").getValue());
                            cpname.append(pname);
                            cpage.append(page);
                            cpacontact.append(pmbno);



                         }else {

                             Toast.makeText(Patientlog.this,"User Doesn't Exist",Toast.LENGTH_SHORT).show();

                         }


                     }else {

                         Toast.makeText(Patientlog.this,"Failed to read",Toast.LENGTH_SHORT).show();
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