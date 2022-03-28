package com.example.ambu_lift;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PatientmainActivity extends AppCompatActivity {

    EditText mdate,time,npname,npickup,ndrop;
    Button conambu;
    DatePickerDialog.OnDateSetListener setListener ;
    DatabaseReference reference;
    ProgressBar npb;
    String[] items =  {"Cardiac Ambulance","General Ambulance","General Ambulance","Oxygen Ambulance"
            ,"Accident Ambulance","AC Ambulance","Mortuary Ambulance","Neonatal Ambulance"};
    AutoCompleteTextView autoCompletetxt;
    ArrayAdapter<String> adapterItems;
    int tHour,tMiniute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_main);
        npname=findViewById(R.id.npname);
        npickup=findViewById(R.id.npickup);
        ndrop=findViewById(R.id.ndrop);
        time=findViewById(R.id.ntime);
        mdate=findViewById(R.id.ndate);
       conambu=findViewById(R.id.conambu);
        npb=findViewById(R.id.npb);
        autoCompletetxt = findViewById(R.id.nambu);

        String pcpass=getIntent().getStringExtra("pass").toString();
        readData(pcpass);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompletetxt.setAdapter(adapterItems);
        autoCompletetxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String AmbulanceType = parent.getItemAtPosition(position).toString();

                conambu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String Patient=npname.getText().toString().trim();
                        String Pickup=npickup.getText().toString().trim();
                        String DropAt=ndrop.getText().toString().trim();
                        String Date=mdate.getText().toString().trim();
                        String Time=time.getText().toString().trim();

                        if(Patient.isEmpty()){
                            npname.setError("Please Enter Patient Name");
                            npname.requestFocus();
                        }
                        else if(Pickup.isEmpty()){
                            npickup.setError("Please Enter Pickup place");
                            npickup.requestFocus();
                        }
                        else if(DropAt.isEmpty()){
                            ndrop.setError("Please Enter Drop Place");
                            ndrop.requestFocus();
                        }
                        else if(Date.isEmpty()){
                            mdate.setError("Please Select Pickup Date");
                            mdate.requestFocus();
                        }
                        else if(Time.isEmpty()){
                            time.setError("Please Select Pickup Time");
                            time.requestFocus();
                        }

                        else {

                            npb.setVisibility(View.VISIBLE);
                            MainPatient mainPatient = new MainPatient(Patient, Pickup, DropAt, Date, Time,AmbulanceType);

                            FirebaseDatabase.getInstance().getReference("Patients").child(Patient)
                                    .setValue(mainPatient).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(PatientmainActivity.this, "You will be Picked up at given schedule", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(PatientmainActivity.this, "Driver Details send by Text", Toast.LENGTH_SHORT).show();
                                        npb.setVisibility(View.GONE);
                                        Intent intent =new Intent(PatientmainActivity.this,fragment2.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(PatientmainActivity.this, "Fail to Book Ambulance ", Toast.LENGTH_SHORT).show();
                                        npb.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }

                    }
                });
            }
        });


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(
                        PatientmainActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                tHour=hourOfDay;
                                tMiniute=minute;
                                String time1=tHour+":"+tMiniute;
                                SimpleDateFormat f24hours =new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try{
                                    Date date= f24hours.parse(time1);
                                    SimpleDateFormat f12Hours= new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    time.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(tHour,tMiniute);
                timePickerDialog.show();
            }
        });

        Calendar calendar=Calendar.getInstance();
        final  int year=calendar.get(Calendar.YEAR);
        final  int month=calendar.get(Calendar.MONTH);
        final  int day=calendar.get(Calendar.DAY_OF_MONTH);




        mdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        PatientmainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date= day+"/"+month+"/"+year;
                        mdate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
                autoCompletetxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String AmbulanceType = parent.getItemAtPosition(position).toString();
                        Toast.makeText(getApplicationContext(),"Selected Ambulance: "+AmbulanceType,Toast.LENGTH_SHORT).show();
            }
        });
        }
    });

    }

    private void readData(String pcpass) {
        reference = FirebaseDatabase.getInstance().getReference("Patients");
        reference.child(pcpass).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        Toast.makeText(PatientmainActivity.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String pname = String.valueOf(dataSnapshot.child("pname").getValue());
                        String ppick = String.valueOf(dataSnapshot.child("paddress").getValue());
                        npname.setText(pname);
                        npickup.append(ppick);



                    }else {

                        Toast.makeText(PatientmainActivity.this,"User Doesn't Exist",Toast.LENGTH_SHORT).show();

                    }


                }else {

                    Toast.makeText(PatientmainActivity.this,"Failed to read",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}