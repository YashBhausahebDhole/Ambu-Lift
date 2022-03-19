package com.example.ambu_lift;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EmergencyBook extends AppCompatActivity {

    Spinner ambuspin;
    Button confirmpai;
    EditText empainame,empaino,situ;
    ProgressBar empb;
      FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("Emergency");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_book);

            empainame=findViewById(R.id.empaname);
            empaino=findViewById(R.id.empano);
            situ=findViewById(R.id.situa);
            ambuspin = findViewById(R.id.ambuspine);

            confirmpai=findViewById(R.id.conbtn);
            empb=findViewById(R.id.empbr);

            String Latitude=getIntent().getStringExtra("Lati");
            String Longitude=getIntent().getStringExtra("Longi");

        confirmpai.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String name=empainame.getText().toString().trim();
                String mbno=empaino.getText().toString().trim();
                String situation=situ.getText().toString().trim();


                if(name.isEmpty()){
                    empainame.setError("Please Enter Patient Name");
                    empainame.requestFocus();
                }
                if(mbno.isEmpty() ){
                    empaino.setError("Please Enter Mobile No");
                    empaino.requestFocus();
                }
                if(mbno.length()>10 ){
                    empaino.setError("Mobile No should not more than 10 digit");
                    empaino.requestFocus();
                }
                if(situation.isEmpty()){
                    situ.setError("Please Enter Patient Name");
                    situ.requestFocus();
                }

                empb.setVisibility(View.VISIBLE);

                HashMap<String ,String> userMap =new HashMap<>();
                userMap.put("Name",name);
                userMap.put("Mobile",mbno);
                userMap.put("Situation",situation);
                userMap.put("Latitude",Latitude);
                userMap.put("Longitude",Longitude);
                root.push().setValue(userMap);

                empb.setVisibility(View.GONE);

            }
        });

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(EmergencyBook.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        ambuspin.setAdapter(myAdapter);



    }
}
