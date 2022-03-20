package com.example.ambu_lift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EmergencyBook extends AppCompatActivity {

    Spinner ambuspin;
    Button confirmpai;
    EditText empainame,empaino,situ;
    ProgressBar empb;




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

                String PatientName=empainame.getText().toString().trim();
                String MobileNo=empaino.getText().toString().trim();
                String Situation=situ.getText().toString().trim();


                if(PatientName.isEmpty()){
                    empainame.setError("Please Enter Patient Name");
                    empainame.requestFocus();
                }
                else if(MobileNo.isEmpty() || MobileNo.length()>10 || MobileNo.length()<10  ){
                    empaino.setError("Please Enter Valid Mobile No");
                    empaino.requestFocus();
                }

                else if(Situation.isEmpty()){
                    situ.setError("Please Enter Patient Name");
                    situ.requestFocus();
                }
                else {
                    empb.setVisibility(View.VISIBLE);
                    EmergencyPatient emergency = new EmergencyPatient(PatientName, MobileNo, Situation, Latitude, Longitude);

                    FirebaseDatabase.getInstance().getReference("Emergency").child(PatientName)
                            .setValue(emergency).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(EmergencyBook.this, "You will be Picked up soon", Toast.LENGTH_SHORT).show();
                                Toast.makeText(EmergencyBook.this, "Driver Details send by Text", Toast.LENGTH_SHORT).show();
                                empb.setVisibility(View.GONE);
                                Intent intent =new Intent(EmergencyBook.this,MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(EmergencyBook.this, "Fail to Book Ambulance ", Toast.LENGTH_SHORT).show();
                                empb.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        });
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(EmergencyBook.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        ambuspin.setAdapter(myAdapter);
    }
}
