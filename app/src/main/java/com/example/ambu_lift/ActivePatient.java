package com.example.ambu_lift;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivePatient extends AppCompatActivity {
ListView activepai;

ArrayList<String> myArraylist=new ArrayList<>();
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_patient);

        ArrayAdapter<String> myArrayAdapter= new ArrayAdapter<String>(ActivePatient.this, android.R.layout.simple_expandable_list_item_1,myArraylist);

        activepai=findViewById(R.id.activepai);
        activepai.setAdapter(myArrayAdapter);



        reference = FirebaseDatabase.getInstance().getReference("Patients");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String cpass=snapshot.child("pcpass").toString();
                    String value= snapshot.child("Main").child("PatientName").getValue().toString();
                    myArrayAdapter.add(value);
                    myArrayAdapter.notifyDataSetChanged();
                    activepai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent=new Intent(ActivePatient.this,Patientlog.class);
                            intent.putExtra("cpass",cpass);
                            intent.putExtra("Name",value);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}