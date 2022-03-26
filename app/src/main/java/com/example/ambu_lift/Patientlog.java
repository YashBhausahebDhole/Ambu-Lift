package com.example.ambu_lift;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Patientlog extends AppCompatActivity {

    Button hire;

    TextView cpname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientlog);

        cpname=findViewById(R.id.cpname);
        hire=findViewById(R.id.hire);

        hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpname.append("Atharva Mundhe");

            }
        });
    }
}