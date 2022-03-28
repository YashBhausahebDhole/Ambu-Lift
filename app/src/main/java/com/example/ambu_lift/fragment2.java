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


public class fragment2 extends Fragment {


     TextView paireg,forgotpassp;
     Button logbtnp;
     EditText patmail,patpass;
     ProgressBar pbarp;
     FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_fragment2, container, false);
        paireg = v.findViewById(R.id.paireg);
        logbtnp = v.findViewById(R.id.logbtnp);
        forgotpassp=v.findViewById(R.id.logbtnp);
        patmail=v.findViewById(R.id.patmail);
        patpass=v.findViewById(R.id.patpass);
        pbarp=v.findViewById(R.id.pbarp);
        mAuth=FirebaseAuth.getInstance();


        forgotpassp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), forgotpass.class);

                startActivity(intent);
            }
        });



        logbtnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email= patmail.getText().toString().trim();
                String Pass=patpass.getText().toString().trim();

                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    patmail.setError("Please Enter valid Email");
                    patmail.requestFocus();
                }
                if(Pass.length()<8){
                    patpass.setError("Please Enter Password of Atleast of 8 Characters");
                    patpass.requestFocus();
                }
                else {
                    pbarp.setVisibility(View.VISIBLE);


                    mAuth.signInWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                pbarp.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), PatientmainActivity.class);
                                intent.putExtra("pass", Pass);
                                startActivity(intent);
                            } else {
                                pbarp.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

        });



        paireg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), paireg.class);
                startActivity(intent);


            }
        });
        return v;
    }


}
