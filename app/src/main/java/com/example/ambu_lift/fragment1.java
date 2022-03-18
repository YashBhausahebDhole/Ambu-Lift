package com.example.ambu_lift;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class fragment1 extends Fragment {

    Spinner ambuspin;
    Button locbtn,confirmpai;
    EditText empainame,empaino,situation;
    TextView signin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment1, container, false);

        signin=v.findViewById(R.id.signin);
        empainame=v.findViewById(R.id.empainame);
        empaino=v.findViewById(R.id.empaino);
        situation=v.findViewById(R.id.situation);
        ambuspin = v.findViewById(R.id.ambuspin);
        locbtn=v.findViewById(R.id.locbtn);
        confirmpai=v.findViewById(R.id.confirmpai);


        locbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),emerlocation.class);
                startActivity(intent);
            }
        });


        confirmpai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Location= getActivity().getIntent().getStringExtra("Location");
                signin.setText(Location);

            }
        });
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        ambuspin.setAdapter(myAdapter);
        return v;


    }
}