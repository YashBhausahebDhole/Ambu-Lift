package com.example.ambu_lift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DriverPortal<reference> extends AppCompatActivity {
    Button  activepatient;
    TextView dname,atype;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_main);
        activepatient=findViewById(R.id.activepatient);
        dname=findViewById(R.id.dname);
        atype=findViewById(R.id.atype);

        String cpass=getIntent().getStringExtra("apass").toString();
        readData(cpass);
        activepatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(DriverPortal.this,ActivePatient.class);
                              startActivity(intent);

            }
        });

    }

    private void readData(String cpass) {
        reference = FirebaseDatabase.getInstance().getReference("Driver");
        reference.child(cpass).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                        Toast.makeText(DriverPortal.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String name = String.valueOf(dataSnapshot.child("name").getValue());
                        String AType = String.valueOf(dataSnapshot.child("AmbulanceType").getValue());
                        dname.setText(name);
                        atype.setText(AType);


                }

            }
        });
    }
    }



