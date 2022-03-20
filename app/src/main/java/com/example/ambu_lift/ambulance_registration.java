package com.example.ambu_lift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ambulance_registration extends AppCompatActivity {

    EditText dname,dno,dmail,dcpass,dpass,dems,dlic,drc,down;
    RadioGroup awhom;
    Button dreg;
    ProgressBar dpb;
    Spinner tyofambu;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ambulance_registration); mAuth = FirebaseAuth.getInstance();
        dname=findViewById(R.id.dname);
        dno=findViewById(R.id.empaino);
        dmail=findViewById(R.id.dmail);
        dpass=findViewById(R.id.dpass);
        dcpass=findViewById(R.id.dcpass);
        dems=findViewById(R.id.dems);
        dlic=findViewById(R.id.dlic);
        awhom=findViewById(R.id.awhom);
        dreg=findViewById(R.id.confirmpai);
        drc=findViewById(R.id.drc);
        down=findViewById(R.id.down);
        dpb=findViewById(R.id.dpb);
        tyofambu = findViewById(R.id.tyofambu);

        //spinner
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ambulance_registration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        tyofambu.setAdapter(myAdapter);

        //button
        dreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=dname.getText().toString().trim();
                String mbno=dno.getText().toString().trim();
                String mail=dmail.getText().toString().trim();
                String pass=dpass.getText().toString().trim();
                String cpass=dcpass.getText().toString().trim();
                String ems=dems.getText().toString().trim();
                String licence=dlic.getText().toString().trim();
                String owner=down.getText().toString().trim();
                String RC=drc.getText().toString().trim();

                int radioButtonID = awhom.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) awhom.findViewById(radioButtonID);
                String cwhom = (String) radioButton.getText();



                if(name.isEmpty()){
                    dname.setError("Please Enter Name");
                    dname.requestFocus();
                }
                if(mbno.isEmpty() ){
                    dno.setError("Please Enter Mobile No");
                    dno.requestFocus();
                }
                if(mbno.length()>10 ){
                    dno.setError("Mobile No should not more than 10 digit");
                    dno.requestFocus();
                }
                if(mail.isEmpty()){
                    dmail.setError("Please Enter Mail ID");
                    dmail.requestFocus();
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    dmail.setError("Please Enter valid Email");
                    dmail.requestFocus();
                }

                if(pass.isEmpty() || pass.length() < 8 ){
                    dpass.setError("Please Enter Password containing atleast 8 digit");
                    dpass.requestFocus();
                }
                if(cpass.isEmpty() || cpass.length() < 8){
                    dcpass.setError("Please Enter Password containing atleast 8 digit");
                    dcpass.requestFocus();
                }
                if(!cpass.equals(pass)){
                    dcpass.setError("Password Didn't Match");
                    dcpass.requestFocus();
                }
                if(ems.isEmpty()){
                    dems.setError("Please Enter EMS ID");
                    dems.requestFocus();
                }
                if(RC.isEmpty()){
                    drc.setError("Please Enter RC of ambulance");
                    drc.requestFocus();
                }
                if(licence.isEmpty()){
                    dlic.setError("Please Enter Licence No");
                    dlic.requestFocus();
                }

                if(owner.isEmpty()){
                    down.setError("Please Enter Owner of Ambulance");
                    down.requestFocus();
                }
                if(cwhom.isEmpty()){
                    Toast.makeText(ambulance_registration.this, "Please Select the Ambulance is own or rent", Toast.LENGTH_SHORT).show();
                }


                dpb.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(mail,cpass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Driver driver = new Driver(name, mbno, mail, cpass, ems, owner, licence, RC, cwhom);

                                    FirebaseDatabase.getInstance().getReference("Driver").child(name)
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(driver).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(ambulance_registration.this, "Driver is Registered Successfully", Toast.LENGTH_SHORT).show();
                                                dpb.setVisibility(View.GONE);
                                                Intent intent = new Intent(ambulance_registration.this, fragment3.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(ambulance_registration.this, "Driver is fail to Register ", Toast.LENGTH_SHORT).show();
                                                dpb.setVisibility(View.GONE);
                                            }
                                        }

                                    });
                                } else {
                                    Toast.makeText(ambulance_registration.this, "Driver is fail to Register ", Toast.LENGTH_SHORT).show();
                                    dpb.setVisibility(View.GONE);
                                }

                            }

                        });
            }
        });
    }}