package com.example.ambu_lift;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceGroup;
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

import java.util.regex.Pattern;


public class fragment2 extends Fragment {


     TextView register,forgotpass;
     Button logbtn;
     EditText email,pass;
     ProgressBar progressBar;
     FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_fragment2, container, false);
        register = v.findViewById(R.id.paireg);
        logbtn = v.findViewById(R.id.logbtn);
        forgotpass=v.findViewById(R.id.forgotpass);
        email=v.findViewById(R.id.plemail);
        pass=v.findViewById(R.id.plpass);
        progressBar=v.findViewById(R.id.progressBar3);
        mAuth=FirebaseAuth.getInstance();


        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), forgotpass.class);
                startActivity(intent);
            }
        });



        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email= email.getText().toString().trim();
                String Pass=pass.getText().toString().trim();

                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    email.setError("Please Enter valid Email");
                    email.requestFocus();
                }
                if(Pass.length()<8){
                    pass.setError("Please Enter Password of Atleast of 8 Characters");
                    pass.requestFocus();
                }
                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), PatientmainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), paireg.class);
                startActivity(intent);


            }
        });
        return v;
    }


}
