package com.example.ambu_lift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class noti_emer extends AppCompatActivity {
    ListView emerpai;
    ArrayList<String> myArraylist=new ArrayList<>();
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noti_emer);


        ArrayAdapter<String> myArrayAdapter= new ArrayAdapter<String>(noti_emer.this, android.R.layout.simple_expandable_list_item_1,myArraylist);
        emerpai=findViewById(R.id.emerpai);
        emerpai.setAdapter(myArrayAdapter);

        reference = FirebaseDatabase.getInstance().getReference("Emergency");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String value= snapshot.child("PatientName").getValue().toString();
//                    String AType = String.valueOf(dataSnapshot.child("AmbulanceType").getValue());
//                    String aname=getIntent().getStringExtra("a").toString();
                        myArrayAdapter.add(value);
                        myArrayAdapter.notifyDataSetChanged();


                    emerpai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            String nam = parent.getItemAtPosition(position).toString();
                            Intent intent=new Intent(noti_emer.this,emergency_alert.class);
                            intent.putExtra("name",nam);
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