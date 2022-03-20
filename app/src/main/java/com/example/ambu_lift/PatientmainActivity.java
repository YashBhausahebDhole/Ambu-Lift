package com.example.ambu_lift;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class PatientmainActivity extends AppCompatActivity {
    public EditText mdate;
    DatePickerDialog.OnDateSetListener setListener ;
    String[] items =  {"Cardiac Ambulance","General Ambulance","General Ambulance","Oxygen Ambulance"
            ,"Accident Ambulance","AC Ambulance","Mortuary Ambulance","Neonatal Ambulance"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientmain);


        mdate=findViewById(R.id.date);
        Calendar calendar=Calendar.getInstance();
        final  int year=calendar.get(Calendar.YEAR);
        final  int month=calendar.get(Calendar.MONTH);
        final  int day=calendar.get(Calendar.DAY_OF_MONTH);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);


        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String AmbulanceType = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Selected Ambulance: "+AmbulanceType,Toast.LENGTH_SHORT).show();


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
            }
        });
            }
        });

    }
}