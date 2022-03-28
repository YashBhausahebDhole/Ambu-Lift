package com.example.ambu_lift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class EmergencyBook extends AppCompatActivity {


    Button confirmpai;
    EditText empainame,empaino,situ;
    ProgressBar empb;
    String[] items =  {"Cardiac Ambulance","General Ambulance","General Ambulance","Oxygen Ambulance"
            ,"Accident Ambulance","AC Ambulance","Mortuary Ambulance","Neonatal Ambulance"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_book);

            empainame=findViewById(R.id.empaname);
            empaino=findViewById(R.id.empano);
            situ=findViewById(R.id.situa);


            confirmpai=findViewById(R.id.conbtn);
            empb=findViewById(R.id.empbr);

        autoCompleteTxt = findViewById(R.id.nambu);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);


            String Latitude=getIntent().getStringExtra("Lati");
            String Longitude=getIntent().getStringExtra("Longi");

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String AmbulanceType = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Selected Ambulance: "+AmbulanceType,Toast.LENGTH_SHORT).show();


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
                    EmergencyPatient emergency = new EmergencyPatient(PatientName, MobileNo, Situation, Latitude, Longitude,AmbulanceType);

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
            }
        });
    }
}
