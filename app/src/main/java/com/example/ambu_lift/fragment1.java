package com.example.ambu_lift;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class fragment1 extends Fragment {

Button book,callambu;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment1, container, false);

        book=v.findViewById(R.id.Book);
        callambu=v.findViewById(R.id.callambu);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),emerlocation.class);
                startActivity(intent);
            }
        });

        callambu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:022-24308888"));
                startActivity(intent);

            }
        });
        return v;


    }
}