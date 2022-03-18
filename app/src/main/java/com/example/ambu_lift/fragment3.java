package com.example.ambu_lift;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class fragment3 extends Fragment implements View.OnClickListener {


     TextView amreg;
     Button button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_fragment3, container, false);
        amreg = v.findViewById(R.id.ambureg);
        button = v.findViewById(R.id.logbtn);
        amreg.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), ambulance_registration.class);
        startActivity(intent);

    }
}