package com.example.ambu_lift;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class fragment3 extends Fragment{


     TextView amreg,forgotpass;
     Button conambu;
     EditText ambumail,ambupass;
     ProgressBar pbar;
    FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_fragment3, container, false);
        amreg = v.findViewById(R.id.ambureg);
        conambu = v.findViewById(R.id.conambu);
        ambupass = v.findViewById(R.id.ambupass);
        ambumail = v.findViewById(R.id.ambumail);
        pbar=v.findViewById(R.id.pbar);
        mAuth = FirebaseAuth.getInstance();
        forgotpass = v.findViewById(R.id.fgbtn);

        conambu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String AEmail = ambumail.getText().toString().trim();
                String APass = ambupass.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(AEmail).matches()) {
                    ambumail.setError("Please Enter valid Email");
                    ambumail.requestFocus();
                }
                if (APass.length() < 8) {
                    ambupass.setError("Please Enter Password of Atleast of 8 Characters");
                    ambupass.requestFocus();
                }
                else {
                    pbar.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(AEmail, APass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                pbar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), DriverPortal.class);
                                intent.putExtra("apass",APass);
                                startActivity(intent);
                            } else {
                                pbar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        amreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ambulance_registration.class);
                startActivity(intent);
            }
        });



        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), forgotpass.class);
                startActivity(intent);
            }
        });




        return v;
    }
}




