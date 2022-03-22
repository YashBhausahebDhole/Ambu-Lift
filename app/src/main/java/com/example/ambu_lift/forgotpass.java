package com.example.ambu_lift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpass extends AppCompatActivity {

    EditText rmail;
    Button confirm;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpass_patient);

        rmail=findViewById(R.id.rmail);
        confirm=findViewById(R.id.confirm);
        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progress4);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String remailid=rmail.getText().toString();
                if(!Patterns.EMAIL_ADDRESS.matcher(remailid).matches()){
                    rmail.setError("Enter valid email Address");
                    rmail.requestFocus();

                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.sendPasswordResetEmail(remailid).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(forgotpass.this, "Link is sent to Mail to reset Password", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(forgotpass.this,fragment2.class);
                            startActivity(intent);
                            progressBar.setVisibility(View.GONE);
                        }
                        else{
                            Toast.makeText(forgotpass.this, "Failed to Reset Password", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
        }


            });
    }

}