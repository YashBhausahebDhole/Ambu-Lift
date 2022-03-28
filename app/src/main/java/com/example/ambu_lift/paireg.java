package com.example.ambu_lift;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class paireg extends AppCompatActivity {

    EditText ptname,ptmbno,ptmail,ptcpass,ptpass,ptaddress,ptage;
    RadioGroup ptgender;
    Button register;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_registration);
       mAuth = FirebaseAuth.getInstance();
        ptname=findViewById(R.id.npname);
        ptmbno=findViewById(R.id.empaino);
        ptmail=findViewById(R.id.npickup);
        ptpass=findViewById(R.id.ndrop);
        ptcpass=findViewById(R.id.dcpass);
        ptaddress=findViewById(R.id.dems);
        ptage=findViewById(R.id.dlic);
        ptgender=findViewById(R.id.ptgender);
        register=findViewById(R.id.conambu);
        progressBar=findViewById(R.id.progressBar2);



         }
         public void Registerbuttonclicked(View v){
             String pname=ptname.getText().toString().trim();
             String pmbno=ptmbno.getText().toString().trim();
             String pmail=ptmail.getText().toString().trim();
             String ppass=ptpass.getText().toString().trim();
             String pcpass=ptcpass.getText().toString().trim();
             String paddress=ptaddress.getText().toString().trim();
             String pAge=ptage.getText().toString().trim();
             int radioButtonID = ptgender.getCheckedRadioButtonId();
             RadioButton radioButton = (RadioButton) ptgender.findViewById(radioButtonID);
             String pgender = (String) radioButton.getText();

             if(pname.isEmpty()){
                 ptname.setError("Please Enter Name");
                 ptname.requestFocus();
             }
             if(pmbno.isEmpty() ){
                 ptmbno.setError("Please Enter Mobile No");
                 ptmbno.requestFocus();
             }
             if(pmbno.length()>10 ){
                 ptmbno.setError("Mobile No should not more than 10 digit");
                 ptmbno.requestFocus();
             }
             if(pmail.isEmpty()){
                 ptmail.setError("Please Enter Mail ID");
                 ptmail.requestFocus();
             }
             if(!Patterns.EMAIL_ADDRESS.matcher(pmail).matches()) {
                 ptmail.setError("Please Enter valid Email");
                 ptmail.requestFocus();
             }

             if(ppass.isEmpty() || ppass.length() < 8 ){
                 ptpass.setError("Please Enter Password containing atleast 8 digit");
                 ptpass.requestFocus();
             }
             if(pcpass.isEmpty() || pcpass.length() < 8){
                 ptcpass.setError("Please Enter Password containing atleast 8 digit");
                 ptcpass.requestFocus();
             }
             if(!pcpass.equals(ppass)){
                 ptcpass.setError("Password Didn't Match");
                 ptcpass.requestFocus();
             }
             if(paddress.isEmpty()){
                 ptaddress.setError("Please Enter Address");
                 ptaddress.requestFocus();
             }
             if(pAge.isEmpty()){
                 ptage.setError("Please Enter Age");
                 ptage.requestFocus();
             }
             if(pgender.isEmpty()){
                 Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
             }

             progressBar.setVisibility(View.VISIBLE);


             mAuth.createUserWithEmailAndPassword(pmail,pcpass)
                     .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {

                             if (task.isSuccessful()) {
                                 Paitient patient = new Paitient(pname, pmbno, pmail, pcpass, paddress, pAge, pgender);

                                 FirebaseDatabase.getInstance().getReference("Patients").child(pcpass)
//                                         .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                         .setValue(patient).addOnCompleteListener(new OnCompleteListener<Void>() {
                                     @Override
                                     public void onComplete(@NonNull Task<Void> task) {
                                         if (task.isSuccessful()) {
                                             Toast.makeText(paireg.this, "Patient is Registered Successfully", Toast.LENGTH_SHORT).show();
                                             progressBar.setVisibility(View.GONE);
                                             Intent intent = new Intent(paireg.this, fragment2.class);
                                             startActivity(intent);
                                         }
                                         else {
                                             Toast.makeText(paireg.this, "Patient is fail to Register ", Toast.LENGTH_SHORT).show();
                                             progressBar.setVisibility(View.GONE);
                                         }
                                     }

                                 });
                             }
                             else{
                                 Toast.makeText(paireg.this, "Patient is fail to Register ", Toast.LENGTH_SHORT).show();
                                 progressBar.setVisibility(View.GONE); }

                         }

                     });
         }
}