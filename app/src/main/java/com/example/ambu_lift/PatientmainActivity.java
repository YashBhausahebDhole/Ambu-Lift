package com.example.ambu_lift;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PatientmainActivity extends AppCompatActivity {

    public EditText mdate,time;
    DatePickerDialog.OnDateSetListener setListener ;
    String[] items =  {"Cardiac Ambulance","General Ambulance","General Ambulance","Oxygen Ambulance"
            ,"Accident Ambulance","AC Ambulance","Mortuary Ambulance","Neonatal Ambulance"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    int tHour,tMiniute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_main);

        mdate=findViewById(R.id.date);
        time=findViewById(R.id.time);
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

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);



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
                autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String AmbulanceType = parent.getItemAtPosition(position).toString();
                        Toast.makeText(getApplicationContext(),"Selected Ambulance: "+AmbulanceType,Toast.LENGTH_SHORT).show();
            }
        });
        }
    });

    }
}